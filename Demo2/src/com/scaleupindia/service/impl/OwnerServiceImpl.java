package com.scaleupindia.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exception.DuplicateOwnerException;
import com.scaleupindia.exception.OwnerNotFoundException;
import com.scaleupindia.repository.OwnerRepository;
import com.scaleupindia.repository.impl.OwnerRepositoryImpl;
import com.scaleupindia.service.OwnerService;

/**
 * @author abhishekvermaa10
 *
 */
public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private static final String OWNER_ALREADY_EXISTS = "Owner already exists with ownerId ";
	private static final String OWNER_NOT_FOUND = "Can't find owner with ownerId ";

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
	}

	@Override
	public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException {
		OwnerDTO existingOwner = ownerRepository.findOwner(ownerDTO.getId());
		if (Objects.nonNull(existingOwner)) {
			throw new DuplicateOwnerException(OWNER_ALREADY_EXISTS + ownerDTO.getId());
		} else {
			ownerRepository.saveOwner(ownerDTO);
		}
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		OwnerDTO owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		} else {
			return owner;
		}
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		OwnerDTO owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		} else {
			ownerRepository.updatePetDetails(ownerId, petName);
		}
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		OwnerDTO owner = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(owner)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		} else {
			ownerRepository.deleteOwner(ownerId);
		}
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners();
	}
	
	@Override
	public List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate) {
		return ownerRepository.findOwner(ownerEmailId, petBirthDate);
	}
}
