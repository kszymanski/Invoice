package com.invoice.beans;

import java.io.Serializable;


public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String idUser;
	protected String name;
	protected String surname;
	protected RoleBean role;
	protected String password;
	public UserBean(){};
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
	
	public void setUserBean(UserBean user)
	{
		this.setIdUser(user.getIdUser());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
		this.setSurname(user.getSurname());
		this.setRole(user.getRole());
	}
}
