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

import com.invoice.beans.basic.LoginBean;
import com.invoice.beans.basic.ProductBean;
import com.invoice.beans.basic.ReceiptBean;
import com.invoice.beans.basic.ReceiptPosition;
import com.invoice.beans.basic.StockBean;
import com.invoice.dbacces.ReceiptDAO;
import com.invoice.dbacces.ReceiptPositionDAO;
import com.invoice.dbacces.StockDAO;
@ManagedBean(name="receiptPositions")
@ViewScoped
public class ReceiptPositionList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<ReceiptPosition> positions;
	private ReceiptPosition selectedPosition;
	private ReceiptBean receipt;
	private ReceiptPosition newReceiptPosition;
	private List<ReceiptPosition> deletedPositions;
	
	public ReceiptPositionList() throws IOException
	{
		
		deletedPositions = new ArrayList<ReceiptPosition>();
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String id = (String) session.getAttribute("id");
		LoginBean user = (session != null) ? (LoginBean)session.getAttribute("loginUser") : null;
		if(id!=null)
		{
			session.removeAttribute("id");
			receipt = ReceiptDAO.getReceiptBean(Integer.parseInt(id));
			if(receipt == null)
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("./faces/errors/notauth.xhtml");
				return;
			}
			positions= ReceiptPositionDAO.getReceiptPositionsByReceipt(receipt.getIdReceipt());
			calculate();
			
		}
		else
		{
			positions = new ArrayList<>();
			receipt = new ReceiptBean();
			receipt.setUser(user.getUser());
		}
		
	}
	

	public List<ReceiptPosition> getPositions() {
		return positions;
	}


	public void setPositions(List<ReceiptPosition> positions) {
		this.positions = positions;
	}


	public ReceiptPosition getSelectedPosition() {
		return selectedPosition;
	}


	public void setSelectedPosition(ReceiptPosition selectedPosition) {
		this.selectedPosition = selectedPosition;
	}


	public ReceiptBean getReceipt() {
		return receipt;
	}


	public void setReceipt(ReceiptBean receipt) {
		this.receipt = receipt;
	}


	public ReceiptPosition getNewReceiptPosition() {
		return newReceiptPosition;
	}


	public void setNewReceiptPosition(ReceiptPosition newReceiptPosition) {
		this.newReceiptPosition = newReceiptPosition;
	}


	public void calculate()
	{
		receipt.setAmount(0);
		for (ReceiptPosition position : positions) {
			position.setValue(position.getPrice()*position.getCount()*(((float)position.getProduct().getDefaultTax()/100)+1));
			BigDecimal bd = new BigDecimal(position.getValue());
		    BigDecimal rounded = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		    position.setValue(rounded.floatValue());
		    receipt.setAmount(receipt.getAmount()+ position.getValue());
		}
		BigDecimal bd = new BigDecimal(receipt.getAmount());
	    BigDecimal rounded = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
	    receipt.setAmount(rounded.floatValue());
	}
	public void reinit(ProductBean product)
	{
		System.out.println("reinit");
		newReceiptPosition = new ReceiptPosition();
		newReceiptPosition.setProduct(product);
		newReceiptPosition.setPrice(newReceiptPosition.getProduct().getDefaultPrice());
	}
	
	public void insertReceipt() throws IOException
	{
		if(receipt.getIdReceipt() == 0)
		{
			int id = ReceiptDAO.insertReceipt(receipt);
			if(id == 0)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
				return;
			}
			else
			{
				for (ReceiptPosition position : positions) {
					position.setIdReceipt(id);
					if(!ReceiptPositionDAO.insertReceiptPositions(position))
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
						return;
					}
					else
					{
						StockBean stock = StockDAO.getStockBean(position.getProduct().getIdProduct(),1);
						stock.setStock(stock.getStock()-position.getCount());
						StockDAO.updateStock(stock);
					}
				}
				receipt.setIdReceipt(id);
				FacesContext.getCurrentInstance().getExternalContext().redirect("./Receipt?id="+id);
			}
		}
		else
		{
			if(!ReceiptDAO.updateReceipt(receipt))
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("fail"));
				return;
			}
			else
			{
				for (ReceiptPosition position : positions)
				{
					position.setIdReceipt(receipt.getIdReceipt());
					ReceiptPosition oldPos = ReceiptPositionDAO.getReceiptPosition(receipt.getIdReceipt(), position.getProduct().getIdProduct());
					if(oldPos != null)
					{
						if(!ReceiptPositionDAO.updateReceiptPositions(position))
						{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
							return;
						}
						else
						{
							float newStock = position.getCount() - oldPos.getCount();
							StockBean stock = StockDAO.getStockBean(position.getProduct().getIdProduct(),1);
							stock.setStock( stock.getStock() - newStock );
							StockDAO.updateStock(stock);
						}
					}
					else
					{
						if(!ReceiptPositionDAO.insertReceiptPositions(position))
						{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("niestety nie uda³o siê"));
							return;
						}
						else
						{
							StockBean stock = StockDAO.getStockBean(position.getProduct().getIdProduct(),1);
							stock.setStock(stock.getStock() - position.getCount());
							StockDAO.updateStock(stock);
						}
					}
				}
				if(!deletedPositions.isEmpty())
				{
					for (ReceiptPosition position : deletedPositions) 
					{
						ReceiptPosition oldPos = ReceiptPositionDAO.getReceiptPosition(receipt.getIdReceipt(), position.getProduct().getIdProduct());
						if(oldPos != null)
						{
							StockBean stock = StockDAO.getStockBean(oldPos.getProduct().getIdProduct(),1);
							stock.setStock(stock.getStock() + oldPos.getCount());
							StockDAO.updateStock(stock);
						}
						ReceiptPositionDAO.deleteReceiptPosition(position);
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


