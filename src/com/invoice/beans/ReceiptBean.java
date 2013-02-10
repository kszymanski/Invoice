package com.invoice.beans;

import java.util.Date;

public class ReceiptBean {
	private int idReceipt;
	private int idExternalDelivery;
	private String idUser;
	private Date Date;
	private Date PayDate;
	private Date SellDate;
	
	public void setIdReceipt (int idReceipt){
		this.idReceipt = idReceipt;
	}
	
	public int getIdReceipt(){
		return idReceipt;
	}
	
	public void setIdExternalDelivery (int idExternalDelivery){
		this.idExternalDelivery = idExternalDelivery;
	}
	
	public int getIdExternalDelivery () {
		return idExternalDelivery;
	}
	
	public void setIdUser (String idUser){
		this.idUser = idUser;
	}
	
	public String getIdUser(){
		return idUser;
	}
	
	public void setDate(Date Date){
		this.Date = Date;
	}
	
	public Date getDate(){
		return Date;
	}
	
	public void setPayDate(Date PayDate){
		this.PayDate = PayDate;
	}
	
	public Date getPayDate(){
		return PayDate;
	}
	
	public void setSellDate(Date SellDate){
		this.SellDate = SellDate;
	}
	
	public Date getSellDate(){
		return SellDate;
	}
}
