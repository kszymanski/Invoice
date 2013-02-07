package com.invoice.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.invoice.dbacces.UserDAO;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean isValid=false;
	private String username;
	private String sessionId;
	private String name;
	private String surname;
	
	
	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String authenticate() throws IOException
	{
		String password = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("password");
		if (UserDAO.isValid(this, password))
		{
			//redirect to requested page after login
			String from = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("from");
			if(from != null && !from.isEmpty())
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect(from);
				return "redirect";
			}
			else return "success";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong Username or Password","Wrong Username or Password"));
			return "fail";
		}
	}
}
