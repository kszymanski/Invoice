package com.invoice.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.invoice.dbacces.RoleDAO;
@ManagedBean(name="rolesList")
@RequestScoped
public class RolesBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<RoleBean> roles;
	private RoleBean selectedRole;
	public RolesBean()
	{
		roles=RoleDAO.getRoles();
		System.out.println(roles.size());
	}
	public List<RoleBean> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	public RoleBean getSelectedRole() {
		return selectedRole;
	}
	public void setSelectedRole(RoleBean selectedRole) {
		this.selectedRole = selectedRole;
	}
	public void start() throws SQLException
	{
		System.out.println("start roles");
	}
	public void refreshList()
	{
		roles = null;
		roles=RoleDAO.getRoles();
	}
}
