package com.invoice.beans.basic;


import java.io.Serializable;


import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.invoice.dbacces.ContractorDAO;

public class ContractorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idContractor;
	private String name;
	private String longName;
	private int nip;
	private int regon;
	private String street;
	private String city;
	private int postCode;
	private String country;
	private String region;
	private String type;
	
	public ContractorBean ()
	{

		country = "Polska";
		type = "Dostawca";
	}
	
	public int getIdContractor() {
		return idContractor;
	}
	public void setIdContractor(int idContractor) {
		this.idContractor = idContractor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public int getNip() {
		return nip;
	}
	public void setNip(int nip) {
		this.nip = nip;
	}
	public int getRegon() {
		return regon;
	}
	public void setRegon(int regon) {
		this.regon = regon;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean deleteContractor(){
		
		if(!ContractorDAO.deleteContractor(this))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nie uda³o sie"));
			return false;
		}
	else
	{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usunieto"));
		return true;
	}	
	}
	
	public void updateContractor()throws ValidatorException
	{
		   if(ContractorDAO.updateContractor(this))FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update done!"));
		else 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "nie uda³o sie");
	}
	
	
	
	
	
}
