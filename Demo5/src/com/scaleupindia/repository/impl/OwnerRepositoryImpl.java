package com.scaleupindia.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pet_clinic_jdbc";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";

	@Override
	public void saveOwner(OwnerDTO owner) {
		String sql = "CALL save_owner(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				CallableStatement callableStatement = connection.prepareCall(sql);) {
			Class.forName(DATABASE_DRIVER);
			callableStatement.setInt(1, owner.getId());
			callableStatement.setString(2, owner.getFirstName());
			callableStatement.setString(3, owner.getLastName());
			callableStatement.setString(4, owner.getGender().toString());
			callableStatement.setString(5, owner.getCity());
			callableStatement.setString(6, owner.getState());
			callableStatement.setString(7, owner.getMobileNumber());
			callableStatement.setString(8, owner.getEmailId());
			callableStatement.setInt(9, owner.getPetId());
			callableStatement.setString(10, owner.getPetName());
			callableStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
			callableStatement.setString(12, owner.getPetGender().toString());
			callableStatement.setString(13, owner.getPetType().toString());
			callableStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getLocalizedMessage());
		}
	}

	@Override
	public OwnerDTO findOwner(int ownerId) {
		OwnerDTO owner = null;
		String sql = "CALL find_owner(?)";
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				CallableStatement callableStatement = connection.prepareCall(sql);) {
			Class.forName(DATABASE_DRIVER);
			callableStatement.setInt(1, ownerId);
			ResultSet resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getLocalizedMessage());
		}
		return owner;
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) {
		String sql = "CALL update_pet(?, ?)";
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				CallableStatement callableStatement = connection.prepareCall(sql);) {
			Class.forName(DATABASE_DRIVER);
			callableStatement.setInt(1, ownerId);
			callableStatement.setString(2, petName);
			callableStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getLocalizedMessage());
		}
	}

	@Override
	public void deleteOwner(int ownerId) {
		String sql = "CALL delete_owner(?)";
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				CallableStatement callableStatement = connection.prepareCall(sql);) {
			Class.forName(DATABASE_DRIVER);
			callableStatement.setInt(1, ownerId);
			callableStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		List<OwnerDTO> ownerList = new ArrayList<>();
		String sql = "CALL find_owners()";
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				CallableStatement callableStatement = connection.prepareCall(sql);) {
			Class.forName(DATABASE_DRIVER);
			ResultSet resultSet = callableStatement.executeQuery(sql);
			while (resultSet.next()) {
				OwnerDTO owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
				ownerList.add(owner);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
			throw new InternalServiceException(exception.getLocalizedMessage());
		}
		return ownerList;
	}
}
