package com.scaleupindia.repository;

import java.util.List;

import com.scaleupindia.dto.OwnerDTO;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerRepository {
	List<OwnerDTO> findOwners(String petType);

	List<OwnerDTO> updatePetDetailsWithoutCallable(String petType);
	
	List<OwnerDTO> updatePetDetailsWithCallable(String petType);
}
