package com.invoice.beans.lists;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	public UserListBean()
	{
		users=UserDAO.getUserList();
		if(!users.isEmpty())selectedUser = users.get(0);
		newUser = new UserBean();
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
