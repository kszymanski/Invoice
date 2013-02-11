package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.RoleBean;

public class RoleDAO {
	private static RoleBean getRs(ResultSet rs) throws SQLException
	{
		RoleBean role=null;
	
		
			role = new RoleBean();
			role.setIdRole(rs.getInt("idRole"));
			role.setName(rs.getString("Name"));
			role.setAddInvoice(rs.getBoolean("AddInvoice"));
			role.setViewInvoice(rs.getBoolean("ViewInvoice"));
			role.setAddProduct(rs.getBoolean("AddProduct"));
			role.setViewProduct(rs.getBoolean("ViewProduct"));
			role.setAddReciept(rs.getBoolean("AddReciept"));
			role.setViewReciept(rs.getBoolean("ViewReciept"));
			role.setAddBuyInvoice(rs.getBoolean("AddbuyInvoice"));
			role.setViewBuyInvoice(rs.getBoolean("ViewBuyInvoice"));
			role.setAddRole(rs.getBoolean("AddRole"));
			role.setViewRole(rs.getBoolean("ViewRole"));
			role.setAddContractor(rs.getBoolean("AddContractor"));
			role.setViewContractor(rs.getBoolean("ViewContractor"));
			role.setAddUser(rs.getBoolean("AddUser"));
			role.setViewUser(rs.getBoolean("ViewUser"));
			role.setAddWarehause(rs.getBoolean("AddWarehause"));
			role.setViewWarehause(rs.getBoolean("ViewWarehause"));
			role.setAddDelivery(rs.getBoolean("AddDelivery"));
			role.setViewDelivery(rs.getBoolean("ViewDelivery"));
		
		return role;
	}
	
	
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
				role = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return role;
		
	}
	public static RoleBean getRole(String name)
	{
		ResultSet rs;
		RoleBean role = null;
		String query="Select * From role Where Name=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setString(1, name);

		// execute select SQL stetement
		
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				role = getRs(rs);
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
		List<RoleBean> list=new ArrayList<RoleBean>();
		String query="Select * From role";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			

			// execute select SQL statement
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				RoleBean role = getRs(rs);
				list.add(role);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
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
