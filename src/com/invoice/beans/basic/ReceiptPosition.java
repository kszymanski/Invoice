package com.invoice.beans.basic;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.invoice.dbacces.StockDAO;

public class ReceiptPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductBean product;
	private int idReceipt;
	private float count;
	private float price;
	private float value;
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
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public void stockValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation" + value);
		if( (float)value > StockDAO.getStockBean(product.getIdProduct()).getStock())
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Iloœæ nie mo¿e przekraczaæ dostepnej na magazynie."));
		
		
	}
}
