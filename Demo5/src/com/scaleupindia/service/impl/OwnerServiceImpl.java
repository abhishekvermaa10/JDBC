package com.scaleupindia.service.impl;

import java.util.List;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.repository.OwnerRepository;
import com.scaleupindia.repository.impl.OwnerRepositoryImpl;
import com.scaleupindia.service.OwnerService;

/**
 * @author abhishekvermaa10
 *
 */
public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;

	public OwnerServiceImpl() {
		this.ownerRepository = new OwnerRepositoryImpl();
	}

	@Override
	public void saveOwnersAutomatically(List<OwnerDTO> ownerDTOs) {
		ownerRepository.saveOwnersAutomatically(ownerDTOs);
	}

	@Override
	public void saveOwnersManually(List<OwnerDTO> ownerDTOs) {
		ownerRepository.saveOwnersManually(ownerDTOs);
	}

	@Override
	public void saveOwnersManuallyWithSavepoint(List<OwnerDTO> ownerDTOs) {
		ownerRepository.saveOwnersManuallyWithSavepoint(ownerDTOs);
	}
}
