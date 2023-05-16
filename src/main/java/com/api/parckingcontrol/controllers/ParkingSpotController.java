package com.api.parckingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.parckingcontrol.dtos.ParkingSpotDto;
import com.api.parckingcontrol.models.ParkingSpotModel;
import com.api.parckingcontrol.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

	final ParkingSpotService parkingSpotService;

	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}

	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
		if (parkingSpotService.existsByLicencePlateCar(parkingSpotDto.getLicencePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use.");
		}

		if (parkingSpotService.existsPakingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use.");
		}

		if (parkingSpotService.existsApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use.");
		}

		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}

	@GetMapping
	public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(
				@RequestParam(defaultValue = "0", required = false) int page,
				@RequestParam(defaultValue = "10", required = false) int size,
				Sort sort
			) {
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(page, size, sort));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneParkingSpots(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpotModel> parkingSpotmodelOptional = parkingSpotService.findById(id);

		if (!parkingSpotmodelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotmodelOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpots(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpotModel> parkinkSlotOptional = parkingSpotService.findById(id);

		if (!parkinkSlotOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Slot not found.");
		}

		parkingSpotService.delete(parkinkSlotOptional.get());

		return ResponseEntity.status(HttpStatus.OK).body("Parking Slot deleted successfully.");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpots(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
		
		Optional<ParkingSpotModel> parkinkSlotOptional = parkingSpotService.findById(id);
		
		if(!parkinkSlotOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Slot not found");
		}
		
		ParkingSpotModel parkingSpoModel = new ParkingSpotModel();
		
		BeanUtils.copyProperties(parkingSpotDto, parkingSpoModel);
		
		parkingSpoModel.setId(parkinkSlotOptional.get().getId());
		parkingSpoModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		ParkingSpotModel parkingSpotModel = parkingSpotService.save(parkingSpoModel);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
	}
}
