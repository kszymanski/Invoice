package com.invoice.beans.basic;

import java.io.Serializable;

public class AdoptionPositionBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idExternalAdoption;
	private ProductBean product;
	private float count;
	private float price;
	private float value;
	
	public int getIdExternalAdoption() {
		return idExternalAdoption;
	}
	
	public void setIdExternalAdoption(int idExternalAdoption) {
		this.idExternalAdoption = idExternalAdoption;
	}
	
	public ProductBean getProduct() {
		return product;
	}
	
	public void setProduct(ProductBean product) {
		this.product = product;
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

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
}
