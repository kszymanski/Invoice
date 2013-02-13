package com.invoice.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.invoice.dbacces.RoleDAO;
@ManagedBean(name="rolesList")
@ViewScoped
public class RolesBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<RoleBean> roles;
	private RoleBean selectedRole;
	private boolean edit=false;
	private FacesMessage message = null;
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
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public void removeRole() throws SQLException
	{
		System.out.println("Deleting role");
		selectedRole.deleteRole();
		roles.remove(selectedRole);
	}
	public void updateRole() throws SQLException
	{
		System.out.println("Updating role");
		roles.remove(selectedRole);
		selectedRole.updateRole();
		roles.add(selectedRole);
		message=selectedRole.message;
		selectedRole.message = null;
		if(message != null )
		{
			System.out.println("Updating role Message");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		message = null;
	}
	public void addRole(RoleBean role) throws Exception
	{
		System.out.println("inserting role");
		role.insertRole();
		roles.add(role);
	}
	public void refreshList()
	{
		System.out.println("refreshing roles list");
		roles=RoleDAO.getRoles();
		RequestContext context = RequestContext.getCurrentInstance();  
        context.update("rolesDataTable");  
	}
}
