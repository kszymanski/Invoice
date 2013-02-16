package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.InvoiceBean;

public class InvoiceDAO {
	public static InvoiceBean getInvoiceBean(int idInvoice){
		ResultSet rs;
		InvoiceBean invoice = null;
		String query="Select * From Invoice Where idInvoice=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idInvoice);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				invoice = new InvoiceBean();

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return invoice;
		
	}
}