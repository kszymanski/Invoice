package com.invoice.beans;

public class OneTimeProductBean {
	private int idOneTimeProduct;
	private InvoiceBean invoice;
	private ReceiptBean receipt;
	private String name;
	private String description;
	private String unit;
	private float price;
	private float count;
	
	public int getIdOneTimeProduct() {
		return idOneTimeProduct;
	}
	public void setIdOneTimeProduct(int idOneTimeProduct) {
		this.idOneTimeProduct = idOneTimeProduct;
	}
	public InvoiceBean getInvoice() {
		return invoice;
	}
	public void setInvoice(InvoiceBean invoice) {
		this.invoice = invoice;
	}
	public ReceiptBean getReceipt() {
		return receipt;
	}
	public void setReceipt(ReceiptBean receipt) {
		this.receipt = receipt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
	}
	
	
	
	
}
