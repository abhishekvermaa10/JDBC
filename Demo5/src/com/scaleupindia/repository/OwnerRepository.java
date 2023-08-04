package com.scaleupindia.repository;

import java.util.List;

import com.scaleupindia.dto.OwnerDTO;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerRepository {
	void saveOwnersAutomatically(List<OwnerDTO> owners);
	
	void saveOwnersManually(List<OwnerDTO> owners);
	
	void saveOwnersManuallyWithSavepoint(List<OwnerDTO> owners);
}
