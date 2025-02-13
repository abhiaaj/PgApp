package com.sapple.tot.requestDto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sapple.tot.helperDto.FacilitiesDto;
import com.sapple.tot.helperDto.HostelRoomCapacityDto;

public class RegisterHostelRequest {
	private long id;
	
	private String name;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String addressLine3;
	
	private String landmark;
	
	private Integer numberOfBeds;
	
	private Integer numberOfRooms;
	
	private Integer messFee;
	
	private Long foodOptionId;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private java.sql.Time entryTime;
	
	@JsonFormat(pattern = "HH:mm:ss")
	private java.sql.Time exitTime;
	
	public java.sql.Time getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(java.sql.Time entryTime) {
		this.entryTime = entryTime;
	}

	public java.sql.Time getExitTime() {
		return exitTime;
	}

	public void setExitTime(java.sql.Time exitTime) {
		this.exitTime = exitTime;
	}

	private List<HostelRoomCapacityDto> hostelRoomCapacity;
	
	private List<Integer> vegFoodDays;
	
	private List<FacilitiesDto> hostelFacilities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getMessFee() {
		return messFee;
	}

	public void setMessFee(Integer messFee) {
		this.messFee = messFee;
	}

	public Long getFoodOptionId() {
		return foodOptionId;
	}

	public void setFoodOptionId(Long foodOptionId) {
		this.foodOptionId = foodOptionId;
	}

	public List<HostelRoomCapacityDto> getHostelRoomCapacity() {
		return hostelRoomCapacity;
	}

	public void setHostelRoomCapacity(List<HostelRoomCapacityDto> hostelRoomCapacity) {
		this.hostelRoomCapacity = hostelRoomCapacity;
	}

	public List<Integer> getVegFoodDays() {
		return vegFoodDays;
	}

	public void setVegFoodDays(List<Integer> vegFoodDays) {
		this.vegFoodDays = vegFoodDays;
	}

	public List<FacilitiesDto> getHostelFacilities() {
		return hostelFacilities;
	}

	public void setHostelFacilities(List<FacilitiesDto> hostelFacilities) {
		this.hostelFacilities = hostelFacilities;
	}
}
