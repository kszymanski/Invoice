package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.UserBean;

public class UserDAO {
	
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
			user = new UserBean();
			user.setIdUser(id);
			user.setPassword(rs.getString("Password"));
			user.setName(rs.getString("Name"));
			user.setSurname(rs.getString("Surname"));
			user.setIdRole(rs.getInt("idRole"));
		}
		stm.close();
		return user;
		
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
				user.setIdRole(rs.getInt("idRole"));
				isValid=true;
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
}
