package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}