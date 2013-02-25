package com.invoice.beans.lists;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.invoice.beans.basic.ExternalAdoptionBean;
import com.invoice.dbacces.ExternalAdoptionDAO;

@ManagedBean(name="adoptionList")
@ViewScoped
public class AdoptionListBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List <ExternalAdoptionBean> adoptions;
	private List <ExternalAdoptionBean> filteredAdoptions;
	private ExternalAdoptionBean selectedAdoption;
	
	public AdoptionListBean()
	{
		adoptions = ExternalAdoptionDAO.getExternalDeliveryList();
		if(!adoptions.isEmpty())selectedAdoption = adoptions.get(0);
	}
	
	public List <ExternalAdoptionBean> getAdoptions() {
		return adoptions;
	}
	public void setAdoptions(List <ExternalAdoptionBean> adoptions) {
		this.adoptions = adoptions;
	}
	public List <ExternalAdoptionBean> getFilteredAdoptions() {
		return filteredAdoptions;
	}
	public void setFilteredAdoptions(List <ExternalAdoptionBean> filteredAdoptions) {
		this.filteredAdoptions = filteredAdoptions;
	}
	public ExternalAdoptionBean getSelectedAdoption() {
		return selectedAdoption;
	}
	public void setSelectedAdoption(ExternalAdoptionBean selectedAdoption) {
		this.selectedAdoption = selectedAdoption;
	}

	public void deleteAdoption()
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Niestety","to nie zosta³o zaimplementowane"));
	}
}
