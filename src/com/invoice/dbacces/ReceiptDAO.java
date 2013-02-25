package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String query="Select * From Receipt Where idReceipt=?";
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
}
