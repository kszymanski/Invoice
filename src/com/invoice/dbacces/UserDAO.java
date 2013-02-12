package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.UserBean;

public class UserDAO {
	
	private static UserBean setRs(ResultSet rs) throws SQLException
	{
		UserBean user = null;
		user = new UserBean();
		user.setIdUser(rs.getString("idUser"));
		user.setPassword(rs.getString("Password"));
		user.setName(rs.getString("Name"));
		user.setSurname(rs.getString("Surname"));
		user.setRole(RoleDAO.getRole(rs.getInt("idRole")));
		user.setActive(rs.getBoolean("Active"));
		return user;
	}
	public static UserBean getUser(String id) throws SQLException
	{
		String query="Select * From User Where idUser=?";
		PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
		stm.setString(1, id);

		// execute select SQL stetement
		ResultSet rs = stm.executeQuery();
		UserBean user = null;
		while (rs.next())
		{
			user = setRs(rs);
		}
		stm.close();
		return user;
		
	}
	public static void getUser(UserBean user) throws SQLException
	{
		String query="Select * From User Where idUser=?";
		PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
		stm.setString(1, user.getIdUser());

		// execute select SQL stetement
		ResultSet rs = stm.executeQuery();
		while (rs.next())
		{
			user.setPassword(rs.getString("Password"));
			user.setName(rs.getString("Name"));
			user.setSurname(rs.getString("Surname"));
			user.setRole(RoleDAO.getRole(rs.getInt("idRole")));
			user.setActive(rs.getBoolean("Active"));
		}
		stm.close();
		
	}
	
	public static List<UserBean> getUserList()
	{
		List<UserBean> list=new ArrayList<UserBean>();
		String query="Select * From User";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				UserBean user = setRs(rs);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean isValid(UserBean user)
	{
		boolean isValid=false;
		//return true;
		try 
		{
			String query="Select * From User Where idUser=? AND Password=?";
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setString(1, user.getIdUser());
			stm.setString(2, user.getPassword());
			
			// execute select SQL stetement
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				user.setName(rs.getString("Name"));
				user.setSurname(rs.getString("Surname"));
				user.setRole(RoleDAO.getRole(rs.getInt("idRole")));
				user.setActive(rs.getBoolean("Active"));
				if(user.isActive())isValid=true;
			}
			stm.close();
			return isValid;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return isValid;
	}
	public static boolean updateUser(UserBean user)
	{
		
		try 
		{
			String query="UPDATE user SET Name = ? , Surname = ? , idRole = ?, Active = ? WHERE idUser = ?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setString(1, user.getName());
			stm.setString(2, user.getSurname());
			stm.setInt(3, user.getRole().getIdRole());
			stm.setBoolean(4, user.isActive());
			stm.setString(5, user.getIdUser());
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			
			
			if(rs == 1)
				{
					con.commit();
					stm.close();
					return true;
				}
			con.rollback();
			stm.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
