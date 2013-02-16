package com.invoice.beans.basic;

public class AdoptionPositionBean {
	private int idExternalDoption;
	private int idProduct;
	private float count;
	private float price;
	private String unit;
	
	public int getIdExternalDoption() {
		return idExternalDoption;
	}
	
	public void setIdExternalDoption(int idExternalDoption) {
		this.idExternalDoption = idExternalDoption;
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
