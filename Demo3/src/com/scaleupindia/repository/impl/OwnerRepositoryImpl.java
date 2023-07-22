package com.scaleupindia.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exception.InternalServiceException;
import com.scaleupindia.repository.OwnerRepository;
import com.scaleupindia.util.MapperUtil;

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
	public void saveOwner(OwnerDTO owner) {
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
	public OwnerDTO findOwner(int ownerId) {
		String sql = "SELECT * FROM owner_table WHERE id = ?";
		OwnerDTO owner = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ownerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
		return owner;
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) {
		String sql = "UPDATE owner_table SET pet_name = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, petName);
			preparedStatement.setInt(2, ownerId);
			preparedStatement.executeUpdate();
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
	public void deleteOwner(int ownerId) {
		String sql = "DELETE FROM owner_table WHERE id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ownerId);
			preparedStatement.executeUpdate();
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
	public List<OwnerDTO> findAllOwners() {
		String sql = "SELECT * FROM owner_table";
		List<OwnerDTO> ownerList = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				OwnerDTO owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
				ownerList.add(owner);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(statement)) {
					statement.close();
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
		return ownerList;
	}
	
	@Override
	public List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate) {
		String sql = "SELECT * FROM owner_table WHERE email_id = ? AND pet_date_of_birth = ?";
		List<OwnerDTO> ownerList = new ArrayList<>();
		OwnerDTO owner = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ownerEmailId);
			preparedStatement.setDate(2, Date.valueOf(petBirthDate));
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
				ownerList.add(owner);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(preparedStatement)) {
					preparedStatement.close();
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			try {
				if (Objects.nonNull(connection)) {
					connection.close();
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
		return ownerList;
	}
}
