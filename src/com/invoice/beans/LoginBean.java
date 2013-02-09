package com.invoice.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.invoice.dbacces.UserDAO;

@ManagedBean(name="loginUser")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean isValid=false;
	private String username;
	private String password;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
		
		if (UserDAO.isValid(this, password))
		{
			// taking session id
			HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessionId = session.getId();
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
			FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"Błędna nazwa użytkownika bądz hasło","Błędna nazwa użytkownika bądz hasło"));
			return null;
		}
	}
	public String logout() throws IOException
	{
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Invoice");
		return "null";
	}
}
