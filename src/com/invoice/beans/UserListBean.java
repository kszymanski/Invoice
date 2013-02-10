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
	private List<ViewUserBean> users;
	private ViewUserBean selectedUser;
	public UserListBean()
	{
		users=UserDAO.getViewUserList();
	}

	public List<ViewUserBean> getUsers() {
		return users;
	}
	public void setUsers(List<ViewUserBean> users) {
		this.users = users;
	}
	public ViewUserBean getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(ViewUserBean selectedUser) {
		this.selectedUser = selectedUser;
	}
	
}
