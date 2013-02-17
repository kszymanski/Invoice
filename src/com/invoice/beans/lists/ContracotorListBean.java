package com.invoice.beans.lists;

import java.util.List;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.invoice.dbacces.ContractorDAO;
import com.invoice.beans.basic.ContractorBean;
@ManagedBean(name="contractorList")
@ViewScoped
public class ContracotorListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ContractorBean> contractors;
	private List<ContractorBean> filtredContractors;
	private ContractorBean selectedContractor;
	
	public ContracotorListBean ()
	{
		
		contractors = ContractorDAO.getContractorList();
		if (!contractors.isEmpty())setSelectedContractor(contractors.get(0));
		
	}
	
	public List<ContractorBean> getContractors() {
		return contractors;
	}
	public void setContractors(List<ContractorBean> contractors) {
		this.contractors = contractors;
	}
	public ContractorBean getSelectedContractor() {
		return selectedContractor;
	}
	public void setSelectedContractor(ContractorBean selectedContractor) {
		this.selectedContractor = selectedContractor;
	}

	public List<ContractorBean> getFiltredContractors() {
		return filtredContractors;
	}

	public void setFiltredContractors(List<ContractorBean> filtredContractors) {
		this.filtredContractors = filtredContractors;
	}
	
	public void deleteContractor()
	{
		if(selectedContractor.deleteContractor())
		{
			contractors.remove(selectedContractor);
			if(contractors.size()>0)selectedContractor = contractors.get(0);
			else selectedContractor = null;
			
		}
		
	}
		
	
	
}
