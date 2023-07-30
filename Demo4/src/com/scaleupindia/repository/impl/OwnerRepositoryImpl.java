package com.scaleupindia.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public List<OwnerDTO> findOwners(String petType) {
		String sql = "SELECT * FROM owner_table WHERE pet_type = ?";
		List<OwnerDTO> ownerList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, petType);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OwnerDTO owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
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
	public List<OwnerDTO> updatePetDetailsWithoutCallable(String petType) {
		String updateSql = """
				UPDATE owner_table SET pet_name =
				CASE pet_gender
					 WHEN 'M' THEN CONCAT('Mr. ', pet_name)
					 WHEN 'F' THEN CONCAT('Miss ', pet_name)
					 ELSE pet_name
				END
				WHERE pet_type = ?""";
		String readSql = "SELECT * FROM owner_table WHERE pet_type = ?";
		List<OwnerDTO> ownerList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			preparedStatement = connection.prepareStatement(updateSql);
			preparedStatement.setString(1, petType);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(readSql);
			preparedStatement.setString(1, petType);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OwnerDTO owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
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
	public List<OwnerDTO> updatePetDetailsWithCallable(String petType) {
		String sql = "CALL add_prefix_to_pet_name(?)";
		List<OwnerDTO> ownerList = new ArrayList<>();
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, petType);
			ResultSet resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				OwnerDTO owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
				ownerList.add(owner);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getMessage());
		} finally {
			try {
				if (Objects.nonNull(callableStatement)) {
					callableStatement.close();
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
}
