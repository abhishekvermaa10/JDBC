package com.scaleupindia.service;

import java.util.List;

import com.scaleupindia.dto.OwnerDTO;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerService {
	void saveOwnersAutomatically(List<OwnerDTO> ownerDTOs);

	void saveOwnersManually(List<OwnerDTO> ownerDTOs);

	void saveOwnersManuallyWithSavepoint(List<OwnerDTO> ownerDTOs);
}