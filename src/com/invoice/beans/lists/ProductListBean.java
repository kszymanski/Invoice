package com.invoice.beans.lists;

import java.util.List;

import com.invoice.beans.basic.ProductBean;
import com.invoice.dbacces.ProductDAO;

public class ProductListBean {
	private List<ProductBean> products;
	private ProductBean selectedProduct;
	
	public ProductListBean()
	{
		products = ProductDAO.getProductList();
	}

	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}

	public ProductBean getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductBean selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
}
