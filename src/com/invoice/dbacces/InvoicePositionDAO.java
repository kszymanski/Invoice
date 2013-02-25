package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.InvoicePosition;

public class InvoicePositionDAO {
	private InvoicePosition getRs(ResultSet rs) throws SQLException
	{
		InvoicePosition pos = new InvoicePosition();
		pos.setIdInvoice(rs.getInt("idInvoice"));
		pos.setProduct(ProductDAO.getProductBean(rs.getInt("idProduct")));
		pos.setCount(rs.getFloat("Count"));
		pos.setPrice(rs.getFloat("Price"));
		return pos;
	}
	
	public InvoicePosition getInvoicePosition(int idInvoice,int idProduct)
	{
		ResultSet rs;
		InvoicePosition pos = null;
		String query="Select * From InvoicePosition Where idInvoice=? AND idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idInvoice);
			stm.setInt(2,idProduct);

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				pos = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return pos;
	}
	public List<InvoicePosition> getInvoicePositionsByInvoice(int idInvoice)
	{
		ResultSet rs;
		List<InvoicePosition> positions = new ArrayList<>();
		String query="Select * From RecieptPosition Where idInvoice=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idInvoice);
			

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				InvoicePosition pos = getRs(rs);
				positions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return positions;
	}
	public List<InvoicePosition> getInvoicePositionsByProduct(int idProduct)
	{
		ResultSet rs;
		List<InvoicePosition> positions = new ArrayList<>();
		String query="Select * From RecieptPosition Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idProduct);
			

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				InvoicePosition pos = getRs(rs);
				positions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return positions;
	}
	public static boolean insertInvoicePositions(InvoicePosition position)
	{
		try 
		{
			String query="INSERT INTO RecieptPosition (`idInvoice`," +
											" `idProduct`," +
											" `Count`," +
											" `Price`) VALUES (?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdInvoice());
			stm.setInt(2, position.getProduct().getIdProduct());
			stm.setFloat(3, position.getCount());
			stm.setFloat(4, position.getPrice());
			
			// execute select SQL statement
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
	public static boolean updateInvoicePositions(InvoicePosition position)
	{
		try 
		{
			String query="UPDATE `RecieptPosition` SET `Count`=?," +
														" `Price`=? WHERE `idInvoice`=? and`idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setFloat(1, position.getCount());
			stm.setFloat(2, position.getPrice());
			stm.setInt(3, position.getIdInvoice());
			stm.setInt(4, position.getProduct().getIdProduct());
			
			// execute select SQL statement
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
	public static boolean deleteRecieptPosition(InvoicePosition position)
	{
		try 
		{
			String query="DELETE FROM `RecieptPosition` WHERE `idInvoice` = ? AND `idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdInvoice());
			stm.setInt(2, position.getProduct().getIdProduct());
			
			
			// execute select SQL statement
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
