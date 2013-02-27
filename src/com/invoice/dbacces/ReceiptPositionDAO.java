package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.ReceiptPosition;

public class ReceiptPositionDAO {
	private static ReceiptPosition getRs(ResultSet rs) throws SQLException
	{
		ReceiptPosition pos = new ReceiptPosition();
		pos.setIdReceipt(rs.getInt("idReceipt"));
		pos.setProduct(ProductDAO.getProductBean(rs.getInt("idProduct")));
		pos.setCount(rs.getFloat("Count"));
		pos.setPrice(rs.getFloat("Price"));
		return pos;
	}
	
	public static ReceiptPosition getReceiptPosition(int idReceipt,int idProduct)
	{
		ResultSet rs;
		ReceiptPosition pos = null;
		String query="Select * From ReceiptPosition Where idReceipt=? AND idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idReceipt);
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
	public static List<ReceiptPosition> getReceiptPositionsByReceipt(int idReceipt)
	{
		ResultSet rs;
		List<ReceiptPosition> positions = new ArrayList<>();
		String query="Select * From ReceiptPosition Where idReceipt=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idReceipt);
			

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				ReceiptPosition pos = getRs(rs);
				positions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return positions;
	}
	public List<ReceiptPosition> getReceiptPositionsByProduct(int idProduct)
	{
		ResultSet rs;
		List<ReceiptPosition> positions = new ArrayList<>();
		String query="Select * From ReceiptPosition Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idProduct);
			

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				ReceiptPosition pos = getRs(rs);
				positions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return positions;
	}
	public static boolean insertReceiptPositions(ReceiptPosition position)
	{
		try 
		{
			String query="INSERT INTO ReceiptPosition (`idReceipt`," +
											" `idProduct`," +
											" `Count`," +
											" `Price`) VALUES (?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdReceipt());
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
	public static boolean updateReceiptPositions(ReceiptPosition position)
	{
		try 
		{
			String query="UPDATE `ReceiptPosition` SET `Count`=?," +
														" `Price`=? WHERE `idReceipt`=? and`idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setFloat(1, position.getCount());
			stm.setFloat(2, position.getPrice());
			stm.setInt(3, position.getIdReceipt());
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
	public static boolean deleteReceiptPosition(ReceiptPosition position)
	{
		try 
		{
			String query="DELETE FROM `ReceiptPosition` WHERE `idReceipt` = ? AND `idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdReceipt());
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
