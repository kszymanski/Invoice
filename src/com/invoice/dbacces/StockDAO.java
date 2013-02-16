package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.StockBean;

public class StockDAO {
	public static StockBean getStockBean(int idStock){
		ResultSet rs;
		StockBean stock = null;
		String query="Select * From Stock Where idStock=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idStock);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				stock = new StockBean();

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stock;
		
	}
}