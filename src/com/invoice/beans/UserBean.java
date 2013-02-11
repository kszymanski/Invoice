package com.invoice.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invoice.dbacces.RoleDAO;
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
	protected int count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public void rolechange()
	{
		System.out.println("role change");
		role=RoleDAO.getRole(role.getName());
	}
	private int isEqual(UserBean user)
	{
		int equal = 0;
		if(!this.idUser.equalsIgnoreCase(user.getIdUser()))equal ++;
		if(!this.name.equalsIgnoreCase(user.getName())) equal ++;
		if(!this.surname.equalsIgnoreCase(user.getSurname()))equal++;
		if(this.role.getIdRole()!= user.role.getIdRole())equal++;
		return equal;
	}
	public void updateUser() throws SQLException
	{
		int toUpdate = isEqual(UserDAO.getUser(idUser));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(toUpdate == 0)
		{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nothing to update.", "Nie by³o zmian"));
			return;
		}
		if(!UserDAO.updateUser(this, toUpdate))
			{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", "Zmiany nie zosta³y zapisane."));
				return;
			}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane."));
	}
}
