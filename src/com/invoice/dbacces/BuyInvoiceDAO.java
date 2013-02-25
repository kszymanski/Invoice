package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.invoice.beans.basic.BuyInvoiceBean;

public class BuyInvoiceDAO {
	private static BuyInvoiceBean getRs(ResultSet rs) throws SQLException
	{
		BuyInvoiceBean buyInvoice = new BuyInvoiceBean();
		buyInvoice.setIdBuyInvoice(rs.getInt("idBuyInvoiec"));
		buyInvoice.setExternalAdoption(ExternalAdoptionDAO.getExternalDeliveryBean(rs.getInt("idExternalAdoption")));
		return buyInvoice;
	}
	
	public static BuyInvoiceBean getBuyInvoiceBean(int idBuyInvoice){
		ResultSet rs;
		BuyInvoiceBean buyinvoice = null;
		String query="Select * From BuyInvoice Where idBuyInvoice=?";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			stm.setInt(1, idBuyInvoice);

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				buyinvoice = getRs(rs);

				
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return buyinvoice;
		
	}
	public static List<BuyInvoiceBean> getBuyInvoiceBeanList(){
		ResultSet rs;
		List<BuyInvoiceBean> buyInvoices = new ArrayList<>();
		String query="Select * From BuyInvoice";
		try {
			PreparedStatement stm= DBCon.getConnection().prepareStatement(query);
			

		// execute select SQL stetement
		
			rs = stm.executeQuery();
			while (rs.next())
			{
				BuyInvoiceBean buyInvoice = getRs(rs);
				buyInvoices.add(buyInvoice);
			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return buyInvoices;
		
	}
}