package com.scaleupindia.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.Gender;
import com.scaleupindia.enums.PetType;
import com.scaleupindia.repository.OwnerRepository;
import com.scaleupindia.util.InputUtil;

/**
 * @author abhishekvermaa10
 *
 */
public class OwnerRepositoryImpl implements OwnerRepository {
	private static List<OwnerDTO> ownerDTOList;

	static {
		ownerDTOList = new ArrayList<>();
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(1);
		ownerDTO.setFirstName("Abhihsek");
		ownerDTO.setLastName("Verma");
		ownerDTO.setGender(Gender.M);
		ownerDTO.setCity("Chandigarh");
		ownerDTO.setState("Chandigarh");
		ownerDTO.setMobileNumber("9876543210");
		ownerDTO.setEmailId("abhishekvermaa10@gmail.com");
		ownerDTO.setPetId(1);
		ownerDTO.setPetName("Mitthu");
		ownerDTO.setPetBirthDate(InputUtil.convertStringToDate("07-03-2022"));
		ownerDTO.setPetGender(Gender.M);
		ownerDTO.setPetType(PetType.BIRD);
		ownerDTOList.add(ownerDTO);
	}

	@Override
	public void saveOwner(OwnerDTO ownerDTO) {
		ownerDTOList.add(ownerDTO);
	}

	@Override
	public Optional<OwnerDTO> findOwner(int ownerId) {
		return ownerDTOList.stream().filter(owner -> owner.getId() == ownerId).findFirst();
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) {
		ownerDTOList.stream().filter(owner -> owner.getId() == ownerId).findFirst().ifPresent(owner -> owner.setPetName(petName));
	}

	@Override
	public void deleteOwner(int ownerId) {
		ownerDTOList.removeIf(owner -> owner.getId() == ownerId);
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerDTOList;
	}
}
