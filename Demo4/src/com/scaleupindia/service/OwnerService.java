package com.scaleupindia.service;

import java.util.List;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.PetType;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerService {
	List<OwnerDTO> findOwners(PetType petType);

	List<OwnerDTO> updatePetDetails(PetType petType, boolean useCallable);
}