package com.invoice.beans.lists;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.invoice.beans.basic.ReceiptBean;
import com.invoice.dbacces.ReceiptDAO;
@ManagedBean(name="receiptList")
@ViewScoped
public class ReceiptListBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List <ReceiptBean> receipts;
	private List <ReceiptBean> filteredReceipts;
	private ReceiptBean selectedReceipt;
	public ReceiptListBean()
	{
		receipts = ReceiptDAO.getReceiptList();
		if(!receipts.isEmpty())selectedReceipt = receipts.get(0);
	}
	public List<ReceiptBean> getReceipts() {
		return receipts;
	}
	public void setReceipts(List<ReceiptBean> receipts) {
		this.receipts = receipts;
	}
	public List<ReceiptBean> getFilteredReceipts() {
		return filteredReceipts;
	}
	public void setFilteredReceipts(List<ReceiptBean> filteredReceipts) {
		this.filteredReceipts = filteredReceipts;
	}
	public ReceiptBean getSelectedReceipt() {
		return selectedReceipt;
	}
	public void setSelectedReceipt(ReceiptBean selectedReceipt) {
		this.selectedReceipt = selectedReceipt;
	}
}
