package com.invoice.beans.lists;

import java.util.List;

import com.invoice.beans.basic.StockBean;
import com.invoice.dbacces.StockDAO;

public class StockListBean {
	private List<StockBean> stocks;
	private StockBean selectedStock;
	
	public StockListBean()
	{
		stocks = StockDAO.getStockList();
	}

	public List<StockBean> getProducts() {
		return stocks;
	}

	public void setProducts(List<StockBean> products) {
		this.stocks = products;
	}

	public StockBean getSelectedProduct() {
		return selectedStock;
	}

	public void setSelectedProduct(StockBean selectedProduct) {
		this.selectedStock = selectedProduct;
	}
}
