package com.invoice.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.invoice.dbacces.UserDAO;
@ManagedBean(name="userList")
@ViewScoped
public class UserListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<UserBean> users;
	private UserBean selectedUser;
	public UserListBean()
	{
		users=UserDAO.getUserList();
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
	
}
