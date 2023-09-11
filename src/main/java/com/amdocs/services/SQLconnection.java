package com.amdocs.services;

import java.sql.*;

public class SQLconnection {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/AmdocsDB";
	private static final String USER_NAME="root";
	private static final String PASSWORD="root";

	private static Connection connection;	
	
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("\nConnection established with Database.\n");
		} catch (SQLException e) {
			System.err.println("\nError : Connection Not Established with Database: " + e.getMessage() );
		}
		return connection;
	}
}
