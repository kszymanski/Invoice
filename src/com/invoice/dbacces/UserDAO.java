package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.UserBean;

public class UserDAO {
	public static boolean isValid(UserBean user, String password)
	{
		try {
			//prepare statment
			String query="Select * From User Where idUser=? AND Password = ?";
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setString(1, user.getUsername());
			stm.setString(2, password);
			
			// execute select SQL stetement
			ResultSet rs = stm.executeQuery();
			
			while (rs.next())
			{
				user.setName(rs.getString("Name"));
				user.setSurname(rs.getString("Surname"));
				user.setValid(true);
			}
			stm.close();
			return user.isValid();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return user.isValid();
	}
}
