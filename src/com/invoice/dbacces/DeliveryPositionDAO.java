package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.DeliveryPositionBean;

public class DeliveryPositionDAO {
	public static DeliveryPositionBean getDeliveryPositionBean(int idDeliveryPosition){
		ResultSet rs;
		DeliveryPositionBean deliveryposition = null;
		String query="Select * From DeliveryPosition Where idDeliveryPosition=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idDeliveryPosition);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				deliveryposition = new DeliveryPositionBean();

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return deliveryposition;
		
	}
}