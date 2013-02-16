package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.OneTimeProductBean;

public class OneTimeProductDAO {
	public static OneTimeProductBean getOneTimeProductBean(int idOneTimeProduct){
		ResultSet rs;
		OneTimeProductBean onetimeproduct = null;
		String query="Select * From OneTimeProduct Where idOneTimeProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idOneTimeProduct);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				onetimeproduct = new OneTimeProductBean();
				
			
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return onetimeproduct;
		
	}
}