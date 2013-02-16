package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.AdoptionPositionBean;

public class AdoptionPositionDAO {
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
				adoptionposition = new AdoptionPositionBean();

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return adoptionposition;
		
	}
}