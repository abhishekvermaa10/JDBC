package com.scaleupindia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author abhishekvermaa10
 *
 */
public class Demo {
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/petistaan_jdbc";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";
	
	public static void main(String[] args) {
		/*
		 * Load the MySQL Driver 
		 * Create a connection with MySQL Database
		 */
		Connection connection = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			System.out.println("Connection established");
			System.out.println("URL: " + connection.getMetaData().getURL());
	        System.out.println("Username: " + connection.getMetaData().getUserName());
	        System.out.println("Database Product Name: " + connection.getMetaData().getDatabaseProductName());
	        System.out.println("Database Product Version: " + connection.getMetaData().getDatabaseProductVersion());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
