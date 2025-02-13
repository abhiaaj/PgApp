package com.sapple.tot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hostel_room_capacity")
public class HostelRoomCapacity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "room_capacity")
	private Integer seater;
	
	@Column(name = "no_of_rooms")
	private Integer noOfRooms;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hostel_id")
	private Hostel hostel;
	
	@Column(name = "fee_per_month")
	private Integer feePerMonth;

	@Column(name = "security_deposit")
	private Integer securityDeposit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
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
