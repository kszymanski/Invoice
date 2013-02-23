package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public static int insertExternalDeliveryList(ExternalAdoptionBean adoption){
		try 
		{
			String query="";
			if(adoption.getContractor().getIdContractor() != 0)
				query="INSERT INTO ExternalAdoption (`idContractor`," +
														" `idUser`," +
														" `Date`," +
														" `PayDate`," +
														" `BuyDate`," +
														" `Amount`) " +
														"VALUES (?, ?, ?, ?, ?, ?)";
			else
				query="INSERT INTO ExternalAdoption (`idUser`," +
													" `Date`," +
													" `PayDate`," +
													" `BuyDate`," +
													" `Amount`) " +
													"VALUES (?, ?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			if(adoption.getContractor().getIdContractor() != 0)
			{
				stm.setInt(1, adoption.getContractor().getIdContractor());
				stm.setString(2, adoption.getUser().getIdUser());
				stm.setDate(3, new java.sql.Date(adoption.getDate().getTime()));
				stm.setDate(4, new java.sql.Date(adoption.getPayDate().getTime()));
				stm.setDate(5, new java.sql.Date(adoption.getBuyDate().getTime()));
				stm.setFloat(6, adoption.getAmount());
			}
			else
			{
				stm.setString(1, adoption.getUser().getIdUser());
				stm.setDate(2, new java.sql.Date(adoption.getDate().getTime()));
				stm.setDate(3, new java.sql.Date(adoption.getPayDate().getTime()));
				stm.setDate(4, new java.sql.Date(adoption.getBuyDate().getTime()));
				stm.setFloat(5, adoption.getAmount());
			}
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			
			
			if(rs == 1)
				{
					ResultSet rsId = stm.getGeneratedKeys();
					rsId.next();
					int auto_id = rsId.getInt(1);
					con.commit();
					stm.close();
					return auto_id;
				}
			con.rollback();
			stm.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static boolean updateExternalDeliveryList(ExternalAdoptionBean adoption){
		try 
		{
			String query="";
			if(adoption.getContractor() != null && adoption.getContractor().getIdContractor() != 0)
				query="UPDATE `ExternalAdoption` SET `idContractor`=?," +
													" `Date`=?," +
													" `PayDate`=?," +
													" `BuyDate`=?," +
													" `Amount`=? WHERE `idExternalAdoption`=?";
			else
				query="UPDATE `ExternalAdoption` SET `Date`=?," +
													" `PayDate`=?," +
													" `BuyDate`=?," +
													" `Amount`=? WHERE `idExternalAdoption`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			if(adoption.getContractor() != null && adoption.getContractor().getIdContractor() != 0)
			{
				stm.setInt(1, adoption.getContractor().getIdContractor());
				stm.setDate(2, new java.sql.Date(adoption.getDate().getTime()));
				stm.setDate(3, new java.sql.Date(adoption.getPayDate().getTime()));
				stm.setDate(4, new java.sql.Date(adoption.getBuyDate().getTime()));
				stm.setFloat(5, adoption.getAmount());
				stm.setInt(6, adoption.getIdExternalAdoption());
			}
			else
			{
				stm.setDate(1, new java.sql.Date(adoption.getDate().getTime()));
				stm.setDate(2, new java.sql.Date(adoption.getPayDate().getTime()));
				stm.setDate(3, new java.sql.Date(adoption.getBuyDate().getTime()));
				stm.setFloat(4, adoption.getAmount());
				stm.setInt(5, adoption.getIdExternalAdoption());
			}
			// execute select SQL statement
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
