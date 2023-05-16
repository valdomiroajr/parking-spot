package com.api.parckingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParkingSpotDto {

	@NotBlank
	private String parkingSpotNumber;

	@NotBlank
	@Size(max = 7)
	private String licencePlateCar;

	@NotBlank
	@Size(max = 10)
	private String brandCar;

	@NotBlank
	@Size(max = 10)
	private String modelCar;

	@NotBlank
	@Size(max = 10)
	private String colorCar;

	@NotBlank
	@Size(max = 130)
	private String responsibleName;

	@NotBlank
	@Size(max = 30)
	private String apartment;

	@NotBlank
	@Size(max = 30)
	private String block;

	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}

	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}

	public String getLicencePlateCar() {
		return licencePlateCar;
	}

	public void setLicencePlateCar(String licencePlateCar) {
		this.licencePlateCar = licencePlateCar;
	}

	public String getBrandCar() {
		return brandCar;
	}

	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}

	public String getColorCar() {
		return colorCar;
	}

	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}
}
