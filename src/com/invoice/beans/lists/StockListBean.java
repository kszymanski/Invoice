package com.invoice.beans.lists;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.invoice.beans.basic.StockBean;
import com.invoice.dbacces.StockDAO;
@ManagedBean(name="stockList")
@ViewScoped
public class StockListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<StockBean> stocks;
	private List<StockBean> filtredStocks;
	private StockBean selectedStock;
	private StockBean newStock;
	
	public StockListBean()
	{
		stocks = StockDAO.getStockList(1);
		if(!stocks.isEmpty())setSelectedStock(stocks.get(0));
		newStock = new StockBean(1);
	}

	public List<StockBean> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockBean> stocks) {
		this.stocks = stocks;
	}

	public List<StockBean> getFiltredStocks() {
		return filtredStocks;
	}

	public void setFiltredStocks(List<StockBean> filtredStocks) {
		this.filtredStocks = filtredStocks;
	}

	public StockBean getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(StockBean selectedStock) {
		this.selectedStock = selectedStock;
	}

	public StockBean getNewStock() {
		return newStock;
	}

	public void setNewStock(StockBean newStock) {
		this.newStock = newStock;
	}
	public String reinit() {  
        newStock = new StockBean(1);  
        return null;  
    }
	public void addStockProduct() {
         if(StockDAO.insertStock(newStock)) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dodano!"));
         else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nie dodano!"));
    }
	public void editStockProduct()
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("zmieniam!"));
	}
}
