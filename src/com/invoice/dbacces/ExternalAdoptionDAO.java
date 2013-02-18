package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.ExternalAdoptionBean;

public class ExternalAdoptionDAO {
	private static ExternalAdoptionBean getRs(ResultSet rs) throws SQLException
	{
		ExternalAdoptionBean externalAdoption = new ExternalAdoptionBean();
		externalAdoption.setIdExternalAdoption(rs.getInt("idExternalAdoption"));
		externalAdoption.setContractor(ContractorDAO.getContractorBean(rs.getInt("idContractor")));
		externalAdoption.setUser(UserDAO.getUser(rs.getString("idUser")));
		externalAdoption.setDate(rs.getDate("Date"));
		externalAdoption.setPayDate(rs.getDate("PayDate"));
		externalAdoption.setBuyDate(rs.getDate("BuyDate"));
		externalAdoption.setAmount(rs.getFloat("Amount"));
		return externalAdoption;
	}
	
	public static ExternalAdoptionBean getExternalDeliveryBean(int idExternalAdoption){
		ResultSet rs;
		ExternalAdoptionBean externalAdoption = null;
		String query="Select * From ExternalAdoption Where idExternalAdoption=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idExternalAdoption);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				externalAdoption = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return externalAdoption;
	}
	
	public static List<ExternalAdoptionBean> getExternalDeliveryList(){
		ResultSet rs;
		List<ExternalAdoptionBean> externalAdoptionList = new ArrayList<ExternalAdoptionBean>();
		String query="Select * From ExternalAdoption";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			
			// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				ExternalAdoptionBean externalAdoption = getRs(rs);
				externalAdoptionList.add(externalAdoption);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return externalAdoptionList;
	}
}
