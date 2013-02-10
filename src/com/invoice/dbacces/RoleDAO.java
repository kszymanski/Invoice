package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.invoice.beans.RoleBean;

public class RoleDAO {
	public static RoleBean getRole(int idRole)
	{
		ResultSet rs;
		RoleBean role = null;
		String query="Select * From role Where idRole=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idRole);

		// execute select SQL stetement
		
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				role = new RoleBean();
				role.setName(rs.getString("Name"));
				role.setAddInvoice(rs.getBoolean("AddInvoice"));
				role.setAddProduct(rs.getBoolean("AddProduct"));
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return role;
		
	}
	public static List<RoleBean> getRoles()
	{
		return null;
	}
	public static boolean insertRole(RoleBean role)
	{
		return false;
	}
	public static boolean updateRole(RoleBean role)
	{
		return false;
	}
}
