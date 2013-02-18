package com.invoice.beans.lists;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.invoice.beans.basic.AdoptionPositionBean;
import com.invoice.beans.basic.ExternalAdoptionBean;
import com.invoice.beans.basic.StockBean;
import com.invoice.dbacces.AdoptionPositionDAO;
import com.invoice.dbacces.ExternalAdoptionDAO;
import com.invoice.dbacces.StockDAO;
@ManagedBean(name="externalAdoption")
@ViewScoped
public class AdoptionPositionList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<StockBean> positions;
	private StockBean selectedPosition;
	private ExternalAdoptionBean adoption;
	
	public AdoptionPositionList() throws IOException
	{
		positions = new ArrayList<StockBean>();
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
			List<AdoptionPositionBean> adoptionPositions= AdoptionPositionDAO.getAdoptionPositionBean(adoption.getIdExternalAdoption());
			for (AdoptionPositionBean adp : adoptionPositions)
			{
				positions.add(StockDAO.getStockBean(adp.getIdProduct(), 1));
			}
			
		}
		else
		{
			adoption = new ExternalAdoptionBean();
		}
		
	}
	
	public List<StockBean> getPositions() {
		return positions;
	}
	public void setPositions(List<StockBean> positions) {
		this.positions = positions;
	}
	public StockBean getSelectedPosition() {
		return selectedPosition;
	}
	public void setSelectedPosition(StockBean selectedPosition) {
		this.selectedPosition = selectedPosition;
	}
	public ExternalAdoptionBean getAdoption() {
		return adoption;
	}
	public void setAdoption(ExternalAdoptionBean adoption) {
		this.adoption = adoption;
	}
}
