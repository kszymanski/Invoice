package com.invoice.beans.basic;

import java.io.Serializable;

public class ReceiptPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductBean product;
	private int idReceipt;
	private float count;
	private float price;
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	public int getIdReceipt() {
		return idReceipt;
	}
	public void setIdReceipt(int idReciept) {
		this.idReceipt = idReciept;
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
