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
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pet_clinic_jdbc";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";

	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			Class.forName(DATABASE_DRIVER);
			System.out.println("Connection established");
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
}
