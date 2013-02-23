package com.invoice.beans.lists;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.invoice.beans.basic.AdoptionPositionBean;
import com.invoice.beans.basic.ExternalAdoptionBean;
import com.invoice.beans.basic.LoginBean;
import com.invoice.beans.basic.ProductBean;
import com.invoice.beans.basic.StockBean;
import com.invoice.dbacces.AdoptionPositionDAO;
import com.invoice.dbacces.ExternalAdoptionDAO;
import com.invoice.dbacces.StockDAO;
@ManagedBean(name="buyInvoicePositions")
@ViewScoped
public class BuyInvoicePositionsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<AdoptionPositionBean> positions;
	private AdoptionPositionBean selectedPosition;
	private ExternalAdoptionBean adoption;
	private AdoptionPositionBean newAdoptionPosition;
	private List<AdoptionPositionBean> deletedPositions;
	
	public BuyInvoicePositionsBean() throws IOException
	{
		
		deletedPositions = new ArrayList<AdoptionPositionBean>();
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String id = (String) session.getAttribute("id");
		LoginBean user = (session != null) ? (LoginBean)session.getAttribute("loginUser") : null;
		if(id!=null)
		{
			session.removeAttribute("id");
			adoption = ExternalAdoptionDAO.getExternalDeliveryBean(Integer.parseInt(id));
			if(adoption == null)
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("./faces/errors/notauth.xhtml");
				return;
			}
			positions= AdoptionPositionDAO.getAdoptionPositionList(adoption.getIdExternalAdoption());
			calculate();
			
		}
		else
		{
			positions = new ArrayList<AdoptionPositionBean>();
			adoption = new ExternalAdoptionBean();
			adoption.setUser(user.getUser());
		}
		
	}
	
	public List<AdoptionPositionBean> getPositions() {
		return positions;
	}
	public void setPositions(List<AdoptionPositionBean> positions) {
		this.positions = positions;
	}
	public AdoptionPositionBean getSelectedPosition() {
		return selectedPosition;
	}
	public void setSelectedPosition(AdoptionPositionBean selectedPosition) {
		this.selectedPosition = selectedPosition;
	}
	public ExternalAdoptionBean getAdoption() {
		return adoption;
	}
	public void setAdoption(ExternalAdoptionBean adoption) {
		this.adoption = adoption;
	}
	
	public AdoptionPositionBean getNewAdoptionPosition() {
		return newAdoptionPosition;
	}

	public void setNewAdoptionPosition(AdoptionPositionBean newAdoptionPosition) {
		this.newAdoptionPosition = newAdoptionPosition;
	}

	public void calculate()
	{
		adoption.setAmount(0);
		for (AdoptionPositionBean position : positions) {
			position.setValue(position.getPrice()*position.getCount()*(((float)position.getProduct().getDefaultTax()/100)+1));
			BigDecimal bd = new BigDecimal(position.getValue());
		    BigDecimal rounded = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		    position.setValue(rounded.floatValue());
			adoption.setAmount(adoption.getAmount()+position.getValue());
		}
		BigDecimal bd = new BigDecimal(adoption.getAmount());
	    BigDecimal rounded = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
	    adoption.setAmount(rounded.floatValue());
	}
	public void reinit(ProductBean product)
	{
		newAdoptionPosition = new AdoptionPositionBean();
		newAdoptionPosition.setProduct(product);
		newAdoptionPosition.setPrice(newAdoptionPosition.getProduct().getDefaultPrice());
	}
	
	public void insertExternalAdoption() throws IOException
	{
		if(adoption.getIdExternalAdoption() == 0)
		{
			int id = ExternalAdoptionDAO.insertExternalDeliveryList(adoption);
			if(id == 0)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
				return;
			}
			else
			{
				for (AdoptionPositionBean position : positions) {
					position.setIdExternalAdoption(id);
					if(!AdoptionPositionDAO.insertAdoptionPosition(position))
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
						return;
					}
					else
					{
						StockBean stock = StockDAO.getStockBean(position.getProduct().getIdProduct(),1);
						stock.setStock(stock.getStock()+position.getCount());
						StockDAO.updateStock(stock);
					}
				}
				adoption.setIdExternalAdoption(id);
				FacesContext.getCurrentInstance().getExternalContext().redirect("./Adoption?id="+id);
			}
		}
		else
		{
			if(!ExternalAdoptionDAO.updateExternalDeliveryList(adoption))
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("fail"));
				return;
			}
			else
			{
				for (AdoptionPositionBean position : positions)
				{
					position.setIdExternalAdoption(adoption.getIdExternalAdoption());
					AdoptionPositionBean oldPos = AdoptionPositionDAO.getAdoptionPositionBean(position);
					if(oldPos != null)
					{
						if(!AdoptionPositionDAO.updateAdoptionPosition(position))
						{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
							return;
						}
						else
						{
							float newStock = position.getCount() - oldPos.getCount();
							StockBean stock = StockDAO.getStockBean(position.getProduct().getIdProduct(),1);
							stock.setStock( stock.getStock()+ newStock );
							StockDAO.updateStock(stock);
						}
					}
					else
					{
						if(!AdoptionPositionDAO.insertAdoptionPosition(position))
						{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
							return;
						}
						else
						{
							StockBean stock = StockDAO.getStockBean(position.getProduct().getIdProduct(),1);
							stock.setStock(stock.getStock()+position.getCount());
							StockDAO.updateStock(stock);
						}
					}
				}
				if(!deletedPositions.isEmpty())
				{
					for (AdoptionPositionBean position : deletedPositions) 
					{
						AdoptionPositionBean oldPos = AdoptionPositionDAO.getAdoptionPositionBean(position);
						if(oldPos != null)
						{
							StockBean stock = StockDAO.getStockBean(oldPos.getProduct().getIdProduct(),1);
							stock.setStock(stock.getStock() - oldPos.getCount());
							StockDAO.updateStock(stock);
						}
						AdoptionPositionDAO.deleteAdoptionPosition(position);
					}
				}
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("zapisano"));
		}
	}
	public void deletePosition()
	{
		deletedPositions.add(selectedPosition);
		positions.remove(selectedPosition);
		calculate();
	}
}


