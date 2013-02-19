package com.invoice.dbacces;

import java.sql.Connection;
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
	public static boolean insertAdoptionPosition(AdoptionPositionBean position)
	{
		try 
		{
			String query="INSERT INTO AdoptionPosition (`idExternalAdoption`," +
											" `idProduct`," +
											" `Count`," +
											" `Price`) VALUES (?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, position.getIdExternalAdoption());
			stm.setInt(2, position.getProduct().getIdProduct());
			stm.setFloat(3, position.getCount());
			stm.setFloat(4, position.getPrice());
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			
			
			if(rs == 1)
				{
					con.commit();
					stm.close();
					return true;
				}
			con.rollback();
			stm.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	
	}
}