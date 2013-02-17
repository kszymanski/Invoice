package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.ExternalDeliveryBean;

public class ExternalDeliveryDAO {
	public static ExternalDeliveryBean getExternalDeliveryBean(int idExternalDelivery){
		ResultSet rs;
		ExternalDeliveryBean externaldelivery = null;
		String query="Select * From ExternalDelivery Where idExternalDelivery=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idExternalDelivery);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				externaldelivery = new ExternalDeliveryBean();
				externaldelivery.setIdExternalDelivery(rs.getInt("idReceipt"));
			//	externaldelivery.setIdWarehause(WarehauseDAO.getWarehauseBean(rs.getInt("idWarehause")));
				externaldelivery.setAuto(rs.getBoolean("Auto"));
				externaldelivery.setDate(rs.getDate("Date"));
				externaldelivery.setIdContractor(rs.getInt("idContractor"));
				externaldelivery.setIdUser(rs.getString("idUser"));
				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return externaldelivery;
		
	}
}