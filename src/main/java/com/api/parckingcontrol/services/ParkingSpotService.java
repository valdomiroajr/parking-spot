package com.api.parckingcontrol.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.parckingcontrol.models.ParkingSpotModel;
import com.api.parckingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	final ParkingSpotRepository repository;

	public ParkingSpotService(ParkingSpotRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return repository.save(parkingSpotModel);
	}
	
	@Transactional
	public Optional<ParkingSpotModel> findById(UUID id) {
		return repository.findById(id);
	}

	@Transactional
	public void delete(ParkingSpotModel parkinkSlotOptional) {
		repository.delete(parkinkSlotOptional);
	}
	
	public boolean existsByLicencePlateCar(String licencePlateCar) {
		return repository.existsByLicencePlateCar(licencePlateCar);
	}

	public boolean existsPakingSpotNumber(String parkingSpotNumber) {
		return repository.existsByParkingSpotNumber(parkingSpotNumber);
	}

	public boolean existsApartmentAndBlock(String apartment, String block) {
		return repository.existsByApartmentAndBlock(apartment, block);
	}
	
	public Page<ParkingSpotModel> findAll(int page, int size, Sort sort) {
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		
        return repository.findAll(pageRequest);
    }
}
