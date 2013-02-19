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
		adoptionPos.setProduct(ProductDAO.getProductBean(rs.getInt("idProduct")));
		adoptionPos.setCount(rs.getFloat("Count"));
		adoptionPos.setPrice(rs.getFloat("Price"));
		return adoptionPos;
	}
	public static List<AdoptionPositionBean> getAdoptionPositionBean(int idExternalAdoption){
		ResultSet rs;
		List<AdoptionPositionBean> adoptionPositions = new ArrayList<AdoptionPositionBean>();
		String query="Select * From AdoptionPosition Where idExternalAdoption=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idExternalAdoption);

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
	public static List<AdoptionPositionBean> getAdoptionPositionListForProduct(int idProduct){
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
	public static List<AdoptionPositionBean> getAdoptionPositionList(int idExternalAdoption){
		ResultSet rs;
		List<AdoptionPositionBean> adoptionPositions = new ArrayList<AdoptionPositionBean>();
		String query="Select * From AdoptionPosition Where idExternalAdoption=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idExternalAdoption);

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