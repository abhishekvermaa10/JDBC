package com.scaleupindia.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		String sql = "INSERT INTO owner_table "
				+ "(id, first_name, last_name, gender, city, state, mobile_number, email_id, "
				+ "pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type) " + "VALUES (" + owner.getId() + " , '"
				+ owner.getFirstName() + "' , '" + owner.getLastName() + "' , '" + owner.getGender().toString()
				+ "' , '" + owner.getCity() + "' , '" + owner.getState() + "' , '" + owner.getMobileNumber() + "' , '"
				+ owner.getEmailId() + "' , " + owner.getPetId() + " , '" + owner.getPetName() + "' , '"
				+ Date.valueOf(owner.getPetBirthDate()) + "' , '" + owner.getPetGender().toString() + "' , '"
				+ owner.getPetType().toString() + "')";
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(statement)) {
					statement.close();
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
	}

	@Override
	public OwnerDTO findOwner(int ownerId) {
		String sql = "SELECT * FROM owner_table WHERE id = " + ownerId;
		OwnerDTO owner = null;
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(statement)) {
					statement.close();
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
		String sql = "UPDATE owner_table SET pet_name = '" + petName + "' WHERE id = " + ownerId;
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(statement)) {
					statement.close();
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
	}

	@Override
	public void deleteOwner(int ownerId) {
		String sql = "DELETE FROM owner_table WHERE id = " + ownerId;
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(statement)) {
					statement.close();
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
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		String sql = "SELECT * FROM owner_table";
		List<OwnerDTO> ownerList = new ArrayList<>();
		OwnerDTO owner = null;
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
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
