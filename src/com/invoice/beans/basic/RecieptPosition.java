package com.invoice.beans.basic;

import java.io.Serializable;

public class RecieptPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductBean product;
	private int idReciept;
	private float count;
	private float price;
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	public int getIdReciept() {
		return idReciept;
	}
	public void setIdReciept(int idReciept) {
		this.idReciept = idReciept;
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
}
