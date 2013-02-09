package com.invoice.beans;

import java.util.Date;

public class ExternalDeliveryBean {
	private int idExternalDelivery;
	private int idWarehause;
	private boolean Auto;
	private Date Date;
	private int Contractor_idContractor;
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
		this.Auto = Auto;
	}
	
	public boolean getAuto (){
		return Auto;
	}
	
	public void setDate(Date Date){
		this.Date = Date;
	}
	
	public Date getDate(){
		return Date;
	}
	
	public void setContractor_idContractor(int Contractor_idContractor){
		this.Contractor_idContractor = Contractor_idContractor;
	}
	
	public int getContractor_idContractor(){
		return Contractor_idContractor;
	}
	
	public void setIdUser(String idUser){
		this.idUser = idUser;
	}
	
	public String getIdUser(){
		return idUser;
	}
				
}
