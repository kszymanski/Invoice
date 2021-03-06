package com.invoice.beans.basic;


import java.io.Serializable;


import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.invoice.dbacces.ContractorDAO;

public class ContractorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idContractor=0;
	private String name;
	private String longName;
	private int nip;
	private int regon;
	private String street;
	private String city;
	private int postCode;
	private String country;
	private String region;
	
	public ContractorBean ()
	{

		country = "Polska";
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

	
	public boolean deleteContractor(){
		
		if(!ContractorDAO.deleteContractor(this))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nie uda�o sie"));
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
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "nie uda�o sie");
	}
	
	public void insertContractor() throws Exception
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(ContractorDAO.insertContractor(this)==0)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta�y zapisane.");
			context.addMessage(null, message);
		}
		else
		{
			context.addMessage("Test-Error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error-Summary", "Error-Detail"));
		      
		}
	}
	
	
	
	
}
