package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.ReceiptBean;

public class ReceiptDAO {
	private static ReceiptBean getRs(ResultSet rs) throws SQLException
	{
		ReceiptBean receipt = new ReceiptBean();
		receipt.setIdReceipt(rs.getInt("idReceipt"));
		receipt.setUser(UserDAO.getUser(rs.getString("idUser")));
		receipt.setDate(rs.getDate("Date"));
		receipt.setPayDate(rs.getDate("PayDate"));
		receipt.setSellDate(rs.getDate("SellDate"));
		receipt.setAmount(rs.getFloat("Amount"));
		return receipt;
	}
	
	public static ReceiptBean getReceiptBean(int idReceipt){
		ResultSet rs;
		ReceiptBean receipt = null;
		String query="Select * From Receipt Where idReceipt=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idReceipt);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				receipt = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return receipt;
	}
	public static List<ReceiptBean> getReceiptList(){
		ResultSet rs;
		List<ReceiptBean> receipts = new ArrayList<>();
		String query="Select * From Receipt";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);

		// execute select SQL statement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				ReceiptBean receipt = getRs(rs);
				receipts.add(receipt);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return receipts;
	}
	public static int insertReceipt(ReceiptBean receipt){
		try 
		{
			String query="";
				query="INSERT INTO Receipt (`idUser`," +
													" `Date`," +
													" `PayDate`," +
													" `SellDate`," +
													" `Amount`) " +
													"VALUES (?, ?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, receipt.getUser().getIdUser());
			stm.setDate(2, new java.sql.Date(receipt.getDate().getTime()));
			stm.setDate(3, new java.sql.Date(receipt.getPayDate().getTime()));
			stm.setDate(4, new java.sql.Date(receipt.getSellDate().getTime()));
			stm.setFloat(5, receipt.getAmount());
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			
			
			if(rs == 1)
				{
					ResultSet rsId = stm.getGeneratedKeys();
					rsId.next();
					int auto_id = rsId.getInt(1);
					con.commit();
					stm.close();
					return auto_id;
				}
			con.rollback();
			stm.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static boolean updateReceipt(ReceiptBean receipt){
		try 
		{
			String query="UPDATE `Receipt` SET `Date`=?," +
											" `PayDate`=?," +
											" `SellDate`=?," +
											" `Amount`=? WHERE `idReceipt`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setDate(1, new java.sql.Date(receipt.getDate().getTime()));
			stm.setDate(2, new java.sql.Date(receipt.getPayDate().getTime()));
			stm.setDate(3, new java.sql.Date(receipt.getSellDate().getTime()));
			stm.setFloat(4, receipt.getAmount());
			stm.setInt(5, receipt.getIdReceipt());
			
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
