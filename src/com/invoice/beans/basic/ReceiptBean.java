package com.invoice.beans.basic;

import java.util.Date;

public class ReceiptBean {
	private int idReceipt;
	private UserBean user;
	private Date date;
	private Date payDate;
	private Date sellDate;
	private float amount;
	
	public void setIdReceipt (int idReceipt){
		this.idReceipt = idReceipt;
	}
	
	public int getIdReceipt(){
		return idReceipt;
	}
	
	

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public void setDate(Date Date){
		this.date = Date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setPayDate(Date PayDate){
		this.payDate = PayDate;
	}
	
	public Date getPayDate(){
		return payDate;
	}
	
	public void setSellDate(Date SellDate){
		this.sellDate = SellDate;
	}
	
	public Date getSellDate(){
		return sellDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
