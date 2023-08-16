package com.scaleupindia.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import java.util.Objects;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exception.InternalServiceException;
import com.scaleupindia.repository.OwnerRepository;

/**
 * @author abhishekvermaa10
 *
 */
public class OwnerRepositoryImpl implements OwnerRepository {
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/petistaan_jdbc";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";

	@Override
	public void saveOwnersAutomatically(List<OwnerDTO> owners) {
		String sql = """
				INSERT INTO owner_table
				(id, first_name, last_name, gender, city, state, mobile_number, email_id,
				pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			for (OwnerDTO owner : owners) {
				preparedStatement.setInt(1, owner.getId());
				preparedStatement.setString(2, owner.getFirstName());
				preparedStatement.setString(3, owner.getLastName());
				preparedStatement.setString(4, owner.getGender().toString());
				preparedStatement.setString(5, owner.getCity());
				preparedStatement.setString(6, owner.getState());
				preparedStatement.setString(7, owner.getMobileNumber());
				preparedStatement.setString(8, owner.getEmailId());
				preparedStatement.setInt(9, owner.getPetId());
				preparedStatement.setString(10, owner.getPetName());
				preparedStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
				preparedStatement.setString(12, owner.getPetGender().toString());
				preparedStatement.setString(13, owner.getPetType().toString());
				preparedStatement.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveOwnersManually(List<OwnerDTO> owners) {
		String sql = """
				INSERT INTO owner_table
				(id, first_name, last_name, gender, city, state, mobile_number, email_id,
				pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			for (OwnerDTO owner : owners) {
				preparedStatement.setInt(1, owner.getId());
				preparedStatement.setString(2, owner.getFirstName());
				preparedStatement.setString(3, owner.getLastName());
				preparedStatement.setString(4, owner.getGender().toString());
				preparedStatement.setString(5, owner.getCity());
				preparedStatement.setString(6, owner.getState());
				preparedStatement.setString(7, owner.getMobileNumber());
				preparedStatement.setString(8, owner.getEmailId());
				preparedStatement.setInt(9, owner.getPetId());
				preparedStatement.setString(10, owner.getPetName());
				preparedStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
				preparedStatement.setString(12, owner.getPetGender().toString());
				preparedStatement.setString(13, owner.getPetType().toString());
				preparedStatement.executeUpdate();
			}
			connection.commit();
		} catch (ClassNotFoundException | SQLException exception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveOwnersManuallyWithSavepoint(List<OwnerDTO> owners) {
		String sql = """
				INSERT INTO owner_table
				(id, first_name, last_name, gender, city, state, mobile_number, email_id,
				pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Savepoint savepoint = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			for (OwnerDTO owner : owners) {
				preparedStatement.setInt(1, owner.getId());
				preparedStatement.setString(2, owner.getFirstName());
				preparedStatement.setString(3, owner.getLastName());
				preparedStatement.setString(4, owner.getGender().toString());
				preparedStatement.setString(5, owner.getCity());
				preparedStatement.setString(6, owner.getState());
				preparedStatement.setString(7, owner.getMobileNumber());
				preparedStatement.setString(8, owner.getEmailId());
				preparedStatement.setInt(9, owner.getPetId());
				preparedStatement.setString(10, owner.getPetName());
				preparedStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
				preparedStatement.setString(12, owner.getPetGender().toString());
				preparedStatement.setString(13, owner.getPetType().toString());
				preparedStatement.executeUpdate();
				if(owner.getId()%2 != 0) {
					savepoint = connection.setSavepoint("Savepoint after ownerId " + owner.getId());
				}
			}
			connection.commit();
		} catch (ClassNotFoundException | SQLException exception) {
			try {
				connection.rollback(savepoint);
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
