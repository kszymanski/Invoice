package com.invoice.beans.lists;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.dialog.Dialog;

import com.invoice.beans.basic.UserBean;
import com.invoice.dbacces.UserDAO;
@ManagedBean(name="userList")
@ViewScoped
public class UserListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<UserBean> users;
	private UserBean selectedUser;
	private List<UserBean> filteredUsers;
	private UserBean newUser;
	public UserListBean() throws IOException
	{
		users=UserDAO.getUserList();
		newUser = new UserBean();
		
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String id = (String) session.getAttribute("id");
		if(id!=null)
		{
			for (UserBean user : users) 
			{
				if(user.getIdUser().compareTo(id) == 0)
				{
					setSelectedUser(user);
				}
				
			}
			session.removeAttribute("id");
			if(selectedUser == null)FacesContext.getCurrentInstance().getExternalContext().redirect("./faces/errors/notauth.xhtml");
			Dialog dialog = (Dialog) FacesContext.getCurrentInstance().getViewRoot().findComponent("detailUser");
			dialog.setVisible(true);
		}
		if(!users.isEmpty() && selectedUser == null)selectedUser = users.get(0);
	}

	public List<UserBean> getUsers() {
		return users;
	}
	public void setUsers(List<UserBean> users) {
		this.users = users;
	}
	public UserBean getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(UserBean selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserBean> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<UserBean> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public UserBean getNewUser() {
		return newUser;
	}

	public void setNewUser(UserBean newUser) {
		this.newUser = newUser;
	}
	
	public String reinit() {  
        newUser = new UserBean();  
        return null;  
    }
}
