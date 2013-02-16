package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.TaxBean;

public class TaxDAO {
	public static TaxBean getTaxBean(int idTax){
		ResultSet rs;
		TaxBean tax = null;
		String query="Select * From Tax Where idTax=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idTax);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				tax = new TaxBean();
				tax.setIdTax(rs.getInt("idTax"));
				tax.setAmount(rs.getString("Amount"));
				tax.setName(rs.getFloat("Name"));
				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return tax;
		
	}
}