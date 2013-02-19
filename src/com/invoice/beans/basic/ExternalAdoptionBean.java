package com.invoice.beans.basic;

import java.io.Serializable;
import java.util.Date;


public class ExternalAdoptionBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idExternalAdoption;
	private ContractorBean contractor;
	private UserBean user;
	private Date date;
	private Date payDate;
	private Date buyDate;
	private float amount;
	
	

	public ExternalAdoptionBean()
	{
		contractor = new ContractorBean();
		date = new Date();
		payDate = new Date();
		buyDate = new Date();
	}
	public int getIdExternalAdoption() {
		return idExternalAdoption;
	}
	public void setIdExternalAdoption(int idExternalAdoption) {
		this.idExternalAdoption = idExternalAdoption;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
}
