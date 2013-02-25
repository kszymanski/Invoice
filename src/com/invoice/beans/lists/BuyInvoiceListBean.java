package com.invoice.beans.lists;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.invoice.beans.basic.BuyInvoiceBean;
import com.invoice.dbacces.BuyInvoiceDAO;

@ManagedBean(name="buyInvoiceList")
@ViewScoped
public class BuyInvoiceListBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<BuyInvoiceBean> invoices;
	private BuyInvoiceBean selectedInvoice;
	private List<BuyInvoiceBean> filteredInvoices;
	
	public BuyInvoiceListBean()
	{
		invoices = BuyInvoiceDAO.getBuyInvoiceBeanList();
		if(!invoices.isEmpty())selectedInvoice = invoices.get(0);
	}
	public List<BuyInvoiceBean> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<BuyInvoiceBean> invoices) {
		this.invoices = invoices;
	}

	public BuyInvoiceBean getSelectedInvoice() {
		return selectedInvoice;
	}

	public void setSelectedInvoice(BuyInvoiceBean selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}

	public List<BuyInvoiceBean> getFilteredInvoices() {
		return filteredInvoices;
	}

	public void setFilteredInvoices(List<BuyInvoiceBean> filteredInvoices) {
		this.filteredInvoices = filteredInvoices;
	}
	public void deleteBuyInvoice()
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Niestety","to nie zosta³o zaimplementowane"));
	}
}
