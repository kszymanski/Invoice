package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.ProductBean;

public class ProductDAO {
	
	private static ProductBean getRs(ResultSet rs) throws SQLException
	{
		ProductBean product=new ProductBean();
	
		product.setIdProduct(rs.getInt("idProduct"));
		product.setName(rs.getString("Name"));
		product.setLongName(rs.getString("LongName"));
		product.setDescryption(rs.getString("Descryption"));
		product.setDefaultPrice(rs.getFloat("DefaultPrice"));
		product.setDefaultTax(rs.getFloat("DefaultTax"));
		product.setCode(rs.getInt("Code"));
		product.setPicture(rs.getString("Picture"));
		product.setUnit(rs.getString("Unit"));
		return product;
	}
	
	public static ProductBean getProductBean(int idProduct){
		ResultSet rs;
		ProductBean product = null;
		String query="Select * From Product Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idProduct);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				product = getRs(rs);

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return product;
		
	}
	
	public static List<ProductBean> getProductList(){
		ResultSet rs;
		List<ProductBean> list=new ArrayList<ProductBean>();
		String query="Select * From Product";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				ProductBean product = getRs(rs);
				list.add(product);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
		
	}
	
	public static int insertProduct(ProductBean product)
	{
		try 
		{
			String query="INSERT INTO Product (`Name`, `LongName`, `Descryption`, `DefaultPrice`, `DefaultTax`, `Code`, `Picture`, `Unit`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, product.getName());
			stm.setString(2, product.getLongName());
			stm.setString(3, product.getDescryption());
			stm.setFloat(4, product.getDefaultPrice());
			stm.setFloat(5, product.getDefaultTax());
			stm.setInt(6, product.getCode());
			stm.setString(7, product.getPicture());
			stm.setString(7, product.getUnit());
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
	
}