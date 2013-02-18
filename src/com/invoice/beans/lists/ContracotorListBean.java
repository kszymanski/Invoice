package com.invoice.beans.lists;

import java.util.List;
import java.io.Serializable;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.dialog.Dialog;

import com.invoice.dbacces.ContractorDAO;
import com.invoice.beans.basic.ContractorBean;

@ManagedBean(name="contractorList")
@ViewScoped
public class ContracotorListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ContractorBean> contractors;
	private List<ContractorBean> filtredContractors;
	private ContractorBean selectedContractor;
	private ContractorBean newContractor;
	
	public ContracotorListBean () throws IOException
	{
		
		contractors = ContractorDAO.getContractorList();
		newContractor = new ContractorBean();
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String id = (String) session.getAttribute("id");
		if(id!=null)
		{
			for (ContractorBean contractor : contractors) 
			{
				if(contractor.getIdContractor() == Integer.parseInt(id))
				{
					setSelectedContractor(contractor);
				}
			}
			session.removeAttribute("id");
			if(selectedContractor == null)FacesContext.getCurrentInstance().getExternalContext().redirect("./faces/errors/notauth.xhtml");
			Dialog dialog = (Dialog) FacesContext.getCurrentInstance().getViewRoot().findComponent("viewProduct");
			dialog.setVisible(true);
		}
		if(!contractors.isEmpty() && selectedContractor == null)setSelectedContractor(contractors.get(0));
		
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

	public ContractorBean getNewContractor() {
		return newContractor;
	}

	public void setNewContractor(ContractorBean newContractor) {
		this.newContractor = newContractor;
	}
		
	
	
}
