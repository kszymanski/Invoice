package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.DeliveryPositionBean;

public class DeliveryPositionDAO {
	private static DeliveryPositionBean getRs(ResultSet rs) throws SQLException
	{
		DeliveryPositionBean deliveryPos =new DeliveryPositionBean();
		deliveryPos.setIdProduct(rs.getInt("idProduct"));
		deliveryPos.setIdExternalDelivery(rs.getInt("idExternalDelivery"));
		deliveryPos.setCount(rs.getFloat("Count"));
		deliveryPos.setPrice(rs.getFloat("Price"));
		return deliveryPos;
	}
	
	public static DeliveryPositionBean getDeliveryPositionBean(int idProduct,int idExternalDelivery){
		ResultSet rs;
		DeliveryPositionBean deliveryposition = null;
		String query="Select * From DeliveryPosition Where idProduct=? AND idExternalDelivery = ?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idProduct);
			stm.setInt(2, idExternalDelivery);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				deliveryposition = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return deliveryposition;
		
	}
	public static List<DeliveryPositionBean> getDeliveryPositionList(int idProduct){
		ResultSet rs;
		List<DeliveryPositionBean> deliveryPositions = new ArrayList<DeliveryPositionBean>();
		String query="Select * From DeliveryPosition Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idProduct);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				DeliveryPositionBean pos = getRs(rs);
				deliveryPositions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return deliveryPositions;
		
	}
}