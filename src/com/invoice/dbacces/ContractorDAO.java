package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.ContractorBean;
import com.invoice.beans.basic.StockBean;

public class ContractorDAO {
	
	private static ContractorBean getRs(ResultSet rs) throws SQLException
	{
		ContractorBean contractor=new ContractorBean();
		contractor.setIdContractor(rs.getInt("idContractor"));
		contractor.setName(rs.getString("Name"));
		contractor.setLongName(rs.getString("LongName"));
		contractor.setNip(rs.getInt("Nip"));
		contractor.setRegon(rs.getInt("Regon"));
		contractor.setStreet(rs.getString("Street"));
		contractor.setCity(rs.getString("City"));
		contractor.setPostCode(rs.getInt("PostCode"));
		contractor.setRegion(rs.getString("Region"));
		contractor.setCountry(rs.getString("Country"));
		contractor.setType(rs.getString("Type"));
		
		return contractor;
	}
	
	
	
	public static ContractorBean getContractorBean(int idContractor){
		ResultSet rs;
		ContractorBean contractor = null;
		String query="Select * From Contractor Where idContractor=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idContractor);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				contractor =  getRs(rs);
					
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return contractor;
	}
	
	public static List<ContractorBean> getContractorList(){
		ResultSet rs;
		List<ContractorBean> list = new ArrayList<ContractorBean>();
		String query="Select * From Contractor";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				ContractorBean contractor = getRs(rs);
				list.add(contractor);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	
	
	// Do uzupe³nienia
	
	
	public static boolean updateContractor(ContractorBean contractor)
	{
		
		try 
		{
			String query="UPDATE Contractor SET Contractor = ? WHERE idContractor = ?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, contractor.getIdContractor());
	
			
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
	
	
	public static int insertContractor(StockBean stock)
	{
		try 
		{
			String query="INSERT INTO Stock (`idProduct`, `idWarehause`, `Stock`) VALUES (?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			int productId = 0;
			if(ProductDAO.getProductBean(stock.getProduct().getIdProduct()) == null)
			{
				productId = ProductDAO.insertProduct(stock.getProduct());
				if(productId == 0) return 0;
			}
			stm.setInt(1, productId);
			stm.setInt(2, stock.getWarehause().getIdWarehause());
			stm.setFloat(3, stock.getStock());
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			
			
			if(rs == 1)
				{
					con.commit();
					stm.close();
					return productId;
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
	public static boolean deleteContractor(ContractorBean contractor)
	{
		try 
		{
			String query="DELETE FROM `Contractor` WHERE `idContractor`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, contractor.getIdContractor());
			
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			if(rs == 1)
				{
					con.commit();
					stm.close();
				//	if(!ContractorDAO.deleteContractor())return false;
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
