package com.invoice.beans.basic;

public class ProductBean {

	private int idProduct;
	private String Name;
	private String LongName;
	private String Descryption;
	private float DefaultPrice;
	private float DefaultTax;
	private int Code;
	private String Picture;
	
	public void setIdProduct(int idProdcut){
		this.idProduct = idProdcut;
	}
	
	public int getIdProduct(){
		return idProduct;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public String getName(){
		return Name;
	}
	
	public void setLongName(String LongName){
		this.LongName = LongName;
	}
	
	public String getLongName(){
		return LongName;
	}
	
	public void setDescryption(String Descryption){
		this.Descryption = Descryption;
	}
	
	public String getDescryption(){
		return Descryption;
	}
	
	public void setDefaultPrice (float DefaultPrice){
		this.DefaultPrice = DefaultPrice;
	}
	
	public float getDefaultPrice(){
		return DefaultPrice;
	}
	
	public void setDefaultTax (float DefaultTax){
		this.DefaultTax = DefaultTax;
	}
	
	public float getDefaultTax(){
		return DefaultTax;
	}
	
	public void setCode(int Code){
		this.Code = Code;
	}
	
	public int getCode(){
		return Code;
	}
	
	public void setPicture(String Picture){
		this.Picture = Picture;
	}
	
	public String getPicture(){
		return Picture;
	}
	
	
}
