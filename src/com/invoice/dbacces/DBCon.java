package com.invoice.dbacces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private static Connection con;
	final static String CONNECTION_STRING = "jdbc:mysql://127.0.0.1/mydb";
	final static String LOGIN = "root";
	final static String PASSWORD = "";
	//final static String CONNECTION_STRING = "jdbc:mysql://127.11.8.129:3306/mydb";
	//final static String LOGIN = "adminSTxmspm";
	//final static String PASSWORD = "YeGtV_uGnPmt";
	   
	static Connection connect() {
		con = null;   
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection(CONNECTION_STRING,LOGIN,PASSWORD);
	         }
		catch(SQLException ec)
			{return con;}
		catch(ClassNotFoundException ex)
		{return con;}
		return con;
	   	}
	static Connection getConnection() {
		try {
			if(con==null || con.isClosed())
			{
				return connect();
			}
		} 
		catch (SQLException e)
		{return connect();}
		return con;
	   }
}
