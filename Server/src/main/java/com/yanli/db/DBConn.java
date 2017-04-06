package com.yanli.db;

import java.sql.*;

public class DBConn {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/attedance";

	private Connection connect = null;
	public Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			connect = DriverManager.getConnection(DB_URL, "root", "123456");
			System.out.println("connection is successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connect;
	}
}

