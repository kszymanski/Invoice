package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.RoleBean;

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
		String query="Select * From Role Where idRole=?";
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
		String query="Select * From Role Where Name=?";
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
		String query="Select * From Role";
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
		try 
		{
			String query="INSERT INTO Role (`Name`, `AddInvoice`, `ViewInvoice`, `AddProduct`, " +
							"`ViewProduct`, `AddReciept`, `ViewReciept`, `AddBuyInvoice`, `ViewBuyInvoice`, " +
								"`AddRole`, `ViewRole`, `AddContractor`, `ViewContractor`, `AddUser`, `ViewUser`, " +
									"`ViewWarehause`, `AddWarehause`, `ViewDelivery`, `AddDelivery`) " +
										"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setString(1, role.getName());
			stm.setBoolean(2, role.isAddInvoice());
			stm.setBoolean(3, role.isViewInvoice());
			stm.setBoolean(4, role.isAddProduct());
			stm.setBoolean(5, role.isViewProduct());
			stm.setBoolean(6, role.isAddReciept());
			stm.setBoolean(7, role.isViewReciept());
			stm.setBoolean(8, role.isAddBuyInvoice());
			stm.setBoolean(9, role.isViewBuyInvoice());
			stm.setBoolean(10, role.isAddRole());
			stm.setBoolean(11, role.isViewRole());
			stm.setBoolean(12, role.isAddContractor());
			stm.setBoolean(13, role.isViewContractor());
			stm.setBoolean(14, role.isAddUser());
			stm.setBoolean(15, role.isViewUser());
			stm.setBoolean(16, role.isViewWarehause());
			stm.setBoolean(17, role.isAddWarehause());
			stm.setBoolean(18, role.isViewDelivery());
			stm.setBoolean(19, role.isAddDelivery());
			
			
			
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
	public static boolean updateRole(RoleBean role)
	{
		try 
		{
			System.out.println("set query");
			String query="UPDATE `Role` SET `Name`=?, `AddInvoice`=?, `ViewInvoice`=?, `AddProduct`=?, `ViewProduct`=?," +
						" `AddReciept`=?, `ViewReciept`=?, `AddBuyInvoice`=?, `ViewBuyInvoice`=?, `AddRole`=?, `ViewRole`=?," +
						" `AddContractor`=?, `ViewContractor`=?, `AddUser`=?, `ViewUser`=?, `ViewWarehause`=?," +
						" `AddWarehause`=?, `ViewDelivery`=?, `AddDelivery`=? WHERE `idRole`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setString(1, role.getName());
			stm.setBoolean(2, role.isAddInvoice());
			stm.setBoolean(3, role.isViewInvoice());
			stm.setBoolean(4, role.isAddProduct());
			stm.setBoolean(5, role.isViewProduct());
			stm.setBoolean(6, role.isAddReciept());
			stm.setBoolean(7, role.isViewReciept());
			stm.setBoolean(8, role.isAddBuyInvoice());
			stm.setBoolean(9, role.isViewBuyInvoice());
			stm.setBoolean(10, role.isAddRole());
			stm.setBoolean(11, role.isViewRole());
			stm.setBoolean(12, role.isAddContractor());
			stm.setBoolean(13, role.isViewContractor());
			stm.setBoolean(14, role.isAddUser());
			stm.setBoolean(15, role.isViewUser());
			stm.setBoolean(16, role.isViewWarehause());
			stm.setBoolean(17, role.isAddWarehause());
			stm.setBoolean(18, role.isViewDelivery());
			stm.setBoolean(19, role.isAddDelivery());
			stm.setInt(20, role.getIdRole());
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			if(rs == 1)
				{
					con.commit();
					stm.close();
					System.out.println("commit");
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
	public static boolean deleteRole(RoleBean role)
	{
		try 
		{
			String query="DELETE FROM `Role` WHERE `idRole`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, role.getIdRole());
			
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
