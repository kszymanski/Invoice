package com.invoice.beans.basic;

import java.io.Serializable;

import com.invoice.dbacces.WarehauseDAO;

public class StockBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private WarehauseBean warehause;
	private ProductBean product;
	private float stock;
	
	public StockBean(){};
	public StockBean(int idWarehause)
	{
		warehause = WarehauseDAO.getWarehauseBean(1);
		product = new ProductBean();
		stock = 0;
	}
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
	
	public float getStock() {
		return stock;
	}
	
	public void setStock(float stock) {
		this.stock = stock;
	}
}
