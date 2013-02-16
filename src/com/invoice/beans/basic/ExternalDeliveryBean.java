package com.invoice.beans.basic;

import java.util.Date;

public class ExternalDeliveryBean {
	private int idExternalDelivery;
	private int idWarehause;
	private boolean auto;
	private Date date;
	private int idContractor;
	private String idUser;

	public void setIdExternalDelivery(int idExternalDelivery){
		this.idExternalDelivery = idExternalDelivery;
		}
	
	public int getIdExternalDelivery(){
		return idExternalDelivery;
	}
	
	public void setIdWarehause (int idWarehause){
		this.idWarehause = idWarehause;
	}
	
	public int getIdWarehause(){
		return idWarehause;
	}
	
	public void setAuto (boolean Auto){
		this.auto = Auto;
	}
	
	public boolean getAuto (){
		return auto;
	}
	
	public void setDate(Date Date){
		this.date = Date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setIdContractor(int contractor){
		this.idContractor = contractor;
	}
	
	public int getIdContractor(){
		return idContractor;
	}
	
	public void setIdUser(String idUser){
		this.idUser = idUser;
	}
	
	public String getIdUser(){
		return idUser;
	}
				
}
