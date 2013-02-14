package com.invoice.beans.lists;

import java.util.List;

import com.invoice.beans.basic.ContractorBean;

public class ContracotorList {
	private List<ContractorBean> contractors;
	private ContractorBean selectedContractor;
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
	
}
