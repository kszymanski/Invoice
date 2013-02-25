package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.RecieptPosition;

public class RecieptPositionDAO {
	private RecieptPosition getRs(ResultSet rs) throws SQLException
	{
		RecieptPosition pos = new RecieptPosition();
		pos.setIdReciept(rs.getInt("idReceipt"));
		pos.setProduct(ProductDAO.getProductBean(rs.getInt("idProduct")));
		pos.setCount(rs.getFloat("Count"));
		pos.setPrice(rs.getFloat("Price"));
		return pos;
	}
	
	public RecieptPosition getReceiptPosition(int idReceipt,int idProduct)
	{
		ResultSet rs;
		RecieptPosition pos = null;
		String query="Select * From RecieptPosition Where idReceipt=? AND idProduct=?";
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
	public List<RecieptPosition> getReceiptPositionsByReceipt(int idReceipt)
	{
		ResultSet rs;
		List<RecieptPosition> positions = new ArrayList<>();
		String query="Select * From RecieptPosition Where idReceipt=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idReceipt);
			

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				RecieptPosition pos = getRs(rs);
				positions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return positions;
	}
	public List<RecieptPosition> getReceiptPositionsByProduct(int idProduct)
	{
		ResultSet rs;
		List<RecieptPosition> positions = new ArrayList<>();
		String query="Select * From RecieptPosition Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1,idProduct);
			

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				RecieptPosition pos = getRs(rs);
				positions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return positions;
	}
	public static boolean insertReceiptPositions(RecieptPosition position)
	{
		try 
		{
			String query="INSERT INTO RecieptPosition (`idReceipt`," +
											" `idProduct`," +
											" `Count`," +
											" `Price`) VALUES (?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdReciept());
			stm.setInt(2, position.getProduct().getIdProduct());
			stm.setFloat(3, position.getCount());
			stm.setFloat(4, position.getPrice());
			
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
	public static boolean updateReceiptPositions(RecieptPosition position)
	{
		try 
		{
			String query="UPDATE `RecieptPosition` SET `Count`=?," +
														" `Price`=? WHERE `idReceipt`=? and`idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setFloat(1, position.getCount());
			stm.setFloat(2, position.getPrice());
			stm.setInt(3, position.getIdReciept());
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
	public static boolean deleteRecieptPosition(RecieptPosition position)
	{
		try 
		{
			String query="DELETE FROM `RecieptPosition` WHERE `idReceipt` = ? AND `idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdReciept());
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
