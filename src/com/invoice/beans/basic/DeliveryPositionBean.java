package com.invoice.beans.basic;

import java.io.Serializable;

public class DeliveryPositionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProduct;
	private int idExternalDelivery;
	private float Count;
	private float Price;
	private String Unit;
	
	public void setIdProduct (int idProduct){
		this.idProduct = idProduct;
	}
	
	public int getIdProduct(){
		return idProduct;
	}
	
	public void setIdExternalDelivery(int idExternalDelivery){
		this.idExternalDelivery = idExternalDelivery;
	}
	
	public int getIdExternalDelivery(){
		return idExternalDelivery;
	}
	
	public void setCount(float Count){
		this.Count = Count;
	}
	
	public float getCount(){
		return Count;
	}
	
	public void setPrice(float Price){
		this.Price = Price;
	}
	
	public float getPrice(){
		return Price;
	}
	
	public void setUnit (String Unit){
		this.Unit = Unit;
	}
	
	public String getUnit (){
		return Unit;
	}
	
}
