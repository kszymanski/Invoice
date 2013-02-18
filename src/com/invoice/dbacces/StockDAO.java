package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.StockBean;
public class StockDAO {
	
	private static StockBean getRs(ResultSet rs) throws SQLException
	{
		StockBean stock=new StockBean();
		stock.setProduct(ProductDAO.getProductBean(rs.getInt("idProduct")));
		stock.setWarehause(WarehauseDAO.getWarehauseBean(rs.getInt("idWarehause")));
		stock.setStock(rs.getFloat("Stock"));
		return stock;
	}
	
	public static StockBean getStockBean(int idProduct){
		ResultSet rs;
		StockBean stock = null;
		String query="Select * From Stock Where idProduct=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idProduct);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				stock = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stock;
	}
	
	public static StockBean getStockBean(int idProduct, int idWarehause){
		ResultSet rs;
		StockBean stock = null;
		String query="Select * From Stock Where idProduct=? AND idWarehause=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idProduct);
			stm.setInt(2, idWarehause);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				stock = getRs(rs);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stock;
	}
	
	public static List<StockBean> getStockList(){
		ResultSet rs;
		List<StockBean> list = new ArrayList<StockBean>();
		String query="Select * From Stock";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				StockBean stock = getRs(rs);
				list.add(stock);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	public static List<StockBean> getStockList(int idWarehause){
		ResultSet rs;
		List<StockBean> list = new ArrayList<StockBean>();
		String query="Select * From Stock Where idWarehause = ?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idWarehause);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				StockBean stock = getRs(rs);
				list.add(stock);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	public static boolean updateStock(StockBean stock)
	{
		
		try 
		{
			String query="UPDATE Stock SET Stock = ? WHERE idProduct = ? AND idWarehause = ?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setFloat(1, stock.getStock());
			stm.setInt(2, stock.getProduct().getIdProduct());
			stm.setInt(3, stock.getWarehause().getIdWarehause());
			
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
	
	public static int insertStock(StockBean stock)
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
	public static boolean deleteStock(StockBean stock)
	{
		try 
		{
			String query="DELETE FROM `Stock` WHERE `idProduct`=?";
			Connection con=DBCon.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stm= con.prepareStatement(query);
			stm.setInt(1, stock.getProduct().getIdProduct());
			
			
			// execute select SQL stetement
			int rs = stm.executeUpdate();
			if(rs == 1)
				{
					con.commit();
					stm.close();
					if(!ProductDAO.deleteProduct(stock.getProduct()))return false;
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