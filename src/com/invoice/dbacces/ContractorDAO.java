package com.invoice.dbacces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.invoice.beans.basic.ContractorBean;

public class ContractorDAO {
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
				contractor = new ContractorBean();

			}
			stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return contractor;
	}
}
