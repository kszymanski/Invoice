package com.invoice.beans.basic;

import java.io.Serializable;

public class AdoptionPositionBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idExternalAdoption;
	private int idProduct;
	private float count;
	private float price;
	private String unit;
	
	public int getIdExternalAdoption() {
		return idExternalAdoption;
	}
	
	public void setIdExternalAdoption(int idExternalAdoption) {
		this.idExternalAdoption = idExternalAdoption;
	}
	
	public int getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
