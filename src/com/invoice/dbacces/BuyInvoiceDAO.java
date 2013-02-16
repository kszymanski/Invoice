package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.BuyInvoiceBean;

public class BuyInvoiceDAO {
	public static BuyInvoiceBean getBuyInvoiceBean(int idBuyInvoice){
		ResultSet rs;
		BuyInvoiceBean buyinvoice = null;
		String query="Select * From BuyInvoice Where idBuyInvoice=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idBuyInvoice);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				buyinvoice = new BuyInvoiceBean();

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return buyinvoice;
		
	}
}