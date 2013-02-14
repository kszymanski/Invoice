package com.invoice.beans.basic;

public class StockBean {
	private WarehauseBean warehause;
	private ProductBean product;
	private float stack;
	
	public WarehauseBean getWarehause() {
		return warehause;
	}
	public void setWarehause(WarehauseBean warehause) {
		this.warehause = warehause;
	}
	
	public ProductBean getProduct() {
		return product;
	}
	
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	public float getStack() {
		return stack;
	}
	
	public void setStack(float stack) {
		this.stack = stack;
	}
}
