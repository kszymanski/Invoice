package com.invoice.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invoice.dbacces.UserDAO;

@ManagedBean(name="user")
@ViewScoped
public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String idUser;
	protected String name;
	protected String surname;
	protected RoleBean role;
	protected String password;
	public UserBean()
	{
		
	};
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String viewUser() throws IOException
	{
		System.out.println("redirect");
		FacesContext.getCurrentInstance().getExternalContext().redirect("./UserList?id="+idUser);
		return null;
	}
	public void start() throws SQLException
	{
		if( idUser==null || idUser == "")
		{
		System.out.println("start");
		HttpServletRequest request=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		idUser =request.getParameter("id");
		UserDAO.getUser(this);
		}
	}
	public void reload() throws SQLException
	{
		System.out.println("reload");
		
		UserDAO.getUser(this);
		
	}
}
