package com.invoice.beans.basic;

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

	private UserBean user;
	private boolean isValid=false;
	private String sessionId;

	public LoginBean()
	{
		user=new UserBean();
	}
	
	public UserBean getUser() {
		return user;
	}


	public void setUser(UserBean user) {
		this.user = user;
	}


	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String authenticate() throws IOException
	{
		
		if (UserDAO.isValid(user))
		{
			isValid=true;

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
			else return "goHome";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"B��dna nazwa u�ytkownika lub has�o","B��dna nazwa u�ytkownika lub has�o"));
			return null;
		}
	}
	public String logout() throws IOException
	{
		
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		String url=FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		return "null";
	}
}
