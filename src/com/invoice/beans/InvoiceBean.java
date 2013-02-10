package com.invoice.beans;

import java.util.Date;

public class InvoiceBean {
	private int idInvoice;
	private ExternalDeliveryBean externaldelivery;
	private ContractorBean contractor;
	private UserBean user;
	private float amount;
	private Date payDate;
	private Date date;
	private Date sellDate;
	
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	public ExternalDeliveryBean getExternaldelivery() {
		return externaldelivery;
	}
	public void setExternaldelivery(ExternalDeliveryBean externaldelivery) {
		this.externaldelivery = externaldelivery;
	}
	public ContractorBean getContractor() {
		return contractor;
	}
	public void setContractor(ContractorBean contractor) {
		this.contractor = contractor;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	
	
	
	
}
