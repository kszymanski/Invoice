package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.ReceiptBean;

public class ReceiptDAO {
	public static ReceiptBean getReceiptBean(int idReceipt){
		ResultSet rs;
		ReceiptBean receipt = null;
		String query="Select * From receipt Where idReceipt=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idReceipt);

		// execute select SQL stetement
		
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				receipt = new ReceiptBean();
				receipt.setIdReceipt(rs.getInt("idReceipt"));
				receipt.setIdExternalDelivery(rs.getInt("idExternalDelivery"));
				receipt.setIdUser(rs.getString("idUser"));
				receipt.setDate(rs.getDate("Date"));
				receipt.setPayDate(rs.getDate("PayDate"));
				receipt.setSellDate(rs.getDate("SellDate"));
				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return receipt;
		
	}
}
