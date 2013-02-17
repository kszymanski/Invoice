package com.invoice.beans.lists;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.invoice.beans.basic.ExternalAdoptionBean;

@ManagedBean(name="adoptionList")
@ViewScoped
public class adoptionListBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List <ExternalAdoptionBean> adoptions;
	private List <ExternalAdoptionBean> filteredAdoptions;
	private ExternalAdoptionBean selectedAdoption;
	private ExternalAdoptionBean newAdoption;
	
	
	
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
	public ExternalAdoptionBean getNewAdoption() {
		return newAdoption;
	}
	public void setNewAdoption(ExternalAdoptionBean newAdoption) {
		this.newAdoption = newAdoption;
	}

}
