package com.java.training;

import java.sql.*; //importing jdbc packages

public class DatabaseConnection {
	static Connection con; // common for all the objects

	public static Connection connect() { // returns a connection con
		try {
			String oracleJDBCDriver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521/orcl.iiht.tech";
			String userName = "scott";
			String password = "tiger";
			Class.forName(oracleJDBCDriver); // load and register the driver
			con = DriverManager.getConnection(url, userName, password); // establish connection to the database
		} catch (Exception e) {
			System.out.println("Connection Failed!");
		}

		return con;
	}
}
