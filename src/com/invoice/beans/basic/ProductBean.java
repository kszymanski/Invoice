package com.invoice.beans.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.FileUploadEvent;

import com.invoice.dbacces.ProductDAO;

public class ProductBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProduct;
	private String Name;
	private String LongName;
	private String Descryption;
	private float DefaultPrice = (float) 0;
	private int DefaultTax =0;
	private int Code;
	private String Picture;
	private String unit;
	public ProductBean()
	{
		unit = "szt.";
		Picture = "default_product.png";
	}
	
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
	
	public void setDefaultTax (int DefaultTax){
		this.DefaultTax = DefaultTax;
	}
	
	public int getDefaultTax(){
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void updateProduct()throws ValidatorException
	{
		if(ProductDAO.updateProduct(this))FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update done!"));
		else throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "nie uda³o sie"));
	}
	
	public void nameValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation" + value);
		if(value== null || "" == (String)value)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Towaru nie mo¿e byæ pusta"));
		if(((String)value).length() <= 2)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Towaru musi mieæ min 3 znaki"));
		if(value != null && ProductDAO.getProductBean((String)value) != null) 
			throw new ValidatorException(
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Towaru Musi Byæ Unikalna"));
		
	}
	public void nameChangeValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation " + value);
		if(value== null || "" == (String)value)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Towaru nie mo¿e byæ pusta"));
		if(((String)value).length() <= 1)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Towaru musi mieæ min 2 znaki"));
		if(value != null && ProductDAO.getProductBean((String)value) != null && ProductDAO.getProductBean((String)value).idProduct != idProduct) 
		{
			throw new ValidatorException(
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Towaru Musi Byæ Unikalna"));
		}
		
	}
	public void priceValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation " + value);
		if(value== null)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Cena Towaru nie mo¿e byæ pusta"));
		if((float)value < 0 )
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Cena Towaru nie mo¿e byæ ujemna"));
		
	}
	public void taxValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation " + value);
		if(value == null)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Podatek Towaru nie mo¿e byæ pusty"));
		if((float)value < 0 || (float)value > 100 )
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Podatek Towaru nie mo¿e byæ ujemny ani wiêkszy od 100"));
		
	}
	public void upload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    } 
	 public void copyFile(String fileName, InputStream in) {
         try {
           
           
              // write the inputStream to a FileOutputStream
              OutputStream out = new FileOutputStream(new File(fileName));
           
              int read = 0;
              byte[] bytes = new byte[1024];
           
              while ((read = in.read(bytes)) != -1) {
                  out.write(bytes, 0, read);
              }
           
              in.close();
              out.flush();
              out.close();
           
              System.out.println("New file created!");
              } catch (IOException e) {
              System.out.println(e.getMessage());
              }
  }
}
