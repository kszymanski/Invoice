package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.WarehauseBean;

public class WarehauseDAO {
	public static WarehauseBean getWarehauseBean(int idWarehause){
		ResultSet rs;
		WarehauseBean warehause = null;
		String query="Select * From Warehause Where idWarehause=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idWarehause);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				warehause = new WarehauseBean();
				warehause.setIdWarehause(rs.getInt("idWarehause"));
				warehause.setName(rs.getString("Name"));
				warehause.setDescription(rs.getString("Description"));
				warehause.setAddress(rs.getString("Address"));
				warehause.setCity(rs.getString("City"));
				warehause.setPostCode(rs.getInt("PostCode"));
				warehause.setDefaultWarehause(rs.getBoolean("Default"));
				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return warehause;
		
	}
}
