package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.AdoptionPositionBean;

public class AdoptionPositionDAO {
	private static AdoptionPositionBean getRs(ResultSet rs) throws SQLException
	{
		AdoptionPositionBean adoptionPos =new AdoptionPositionBean();
		adoptionPos.setIdExternalAdoption(rs.getInt("idExternalAdoption"));
		adoptionPos.setIdProduct(rs.getInt("idProduct"));
		adoptionPos.setCount(rs.getFloat("Count"));
		adoptionPos.setPrice(rs.getFloat("Price"));
		return adoptionPos;
	}
	public static AdoptionPositionBean getAdoptionPositionBean(int idAdoptionPosition){
		ResultSet rs;
		AdoptionPositionBean adoptionposition = null;
		String query="Select * From AdoptionPosition Where idAdoptionPosition=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idAdoptionPosition);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				adoptionposition = getRs(rs);

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return adoptionposition;
	}
	public static List<AdoptionPositionBean> getAdoptionPositionList(int idProduct){
		ResultSet rs;
		List<AdoptionPositionBean> adoptionPositions = new ArrayList<AdoptionPositionBean>();
		String query="Select * From AdoptionPosition Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idProduct);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				AdoptionPositionBean pos = getRs(rs);
				adoptionPositions.add(pos);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return adoptionPositions;
		
	}
}