package com.sapple.tot.helperDto;

public class HostelRoomCapacityDto {
	private Long id;
	
	private Integer seater;
	
	private Integer noOfRooms;
	
	private Integer feePerMonth;
	
	private Integer securityDeposit;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSeater() {
		return seater;
	}

	public void setSeater(Integer seater) {
		this.seater = seater;
	}

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getFeePerMonth() {
		return feePerMonth;
	}

	public void setFeePerMonth(Integer feePerMonth) {
		this.feePerMonth = feePerMonth;
	}

	public Integer getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Integer securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
}
