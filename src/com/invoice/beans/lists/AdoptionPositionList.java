package com.invoice.beans.lists;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.invoice.beans.basic.AdoptionPositionBean;
import com.invoice.beans.basic.ExternalAdoptionBean;
import com.invoice.beans.basic.ProductBean;
import com.invoice.dbacces.AdoptionPositionDAO;
import com.invoice.dbacces.ExternalAdoptionDAO;
@ManagedBean(name="externalAdoption")
@ViewScoped
public class AdoptionPositionList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<AdoptionPositionBean> positions;
	private AdoptionPositionBean selectedPosition;
	private ExternalAdoptionBean adoption;
	private AdoptionPositionBean newAdoptionPosition;
	
	public AdoptionPositionList() throws IOException
	{
		
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String id = (String) session.getAttribute("id");
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
			
		}
		else
		{
			positions = new ArrayList<AdoptionPositionBean>();
			adoption = new ExternalAdoptionBean();
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
}
