package com.invoice.beans.lists;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.dialog.Dialog;

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
	
	public StockListBean() throws IOException
	{
		stocks = StockDAO.getStockList(1);
		newStock = new StockBean(1);
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String id = (String) session.getAttribute("id");
		if(id!=null)
		{
			for (StockBean stock : stocks) 
			{
				if(stock.getProduct().getIdProduct() == Integer.parseInt(id))
				{
					setSelectedStock(stock);
				}
			}
			session.removeAttribute("id");
			if(selectedStock == null)FacesContext.getCurrentInstance().getExternalContext().redirect("./faces/errors/notauth.xhtml");
			Dialog dialog = (Dialog) FacesContext.getCurrentInstance().getViewRoot().findComponent("viewProduct");
			dialog.setVisible(true);
		}
		if(!stocks.isEmpty() && selectedStock == null)setSelectedStock(stocks.get(0));
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
	public void copyName()
	{
		newStock.getProduct().setLongName(newStock.getProduct().getName());
	}
	public void setNewStock(StockBean newStock) {
		this.newStock = newStock;
	}
	public String reinit() {  
        selectedStock = stocks.get(stocks.indexOf(newStock));
		newStock = new StockBean(1);  
        return null;  
    }
	public void reinitFilter()
	{
		filtredStocks = null;
	}
	public void addStockProduct() {
		int productId = StockDAO.insertStock(newStock);
         if(productId != 0){
        	 newStock.getProduct().setIdProduct(productId);
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dodano!"));
         }
         else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nie dodano!"));
    }
	public void deleteStockProduct()
	{
		if(selectedStock.deleteStock())
		{
			stocks.remove(selectedStock);
			if(stocks.size()>0)selectedStock = stocks.get(0);
			else selectedStock = null;
			
		}
		
	}
	
}
