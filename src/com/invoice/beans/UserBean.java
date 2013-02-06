package com.invoice.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public String akcja() throws IOException
	{
		//redirect to requested page after login
		String from = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("from");
		if(from != null && !from.isEmpty())FacesContext.getCurrentInstance().getExternalContext().redirect(from);
		else return "success";
		
		return "error";
	}
}
