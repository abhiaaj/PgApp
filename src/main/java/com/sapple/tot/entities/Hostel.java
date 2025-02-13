package com.sapple.tot.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "hostel")
public class Hostel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address_line1")
	private String addressLine1;
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "address_line3")
	private String addressLine3;
	
	@Column(name = "landmark")
	private String landmark;
	
	@Column(name = "number_of_beds")
	private Integer numberOfBeds;
	
	@Column(name = "number_of_rooms")
	private Integer numberOfRooms;
	
	@DateTimeFormat(pattern="HH:mm:ss" )
	@Column(name = "entry_time")
	private java.sql.Time entryTime;

	@DateTimeFormat(pattern="HH:mm:ss" )
	@Column(name = "exit_time")
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

	@Column(name = "is_active")
	private Integer isActive;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_option_id")
	private FoodOptions foodOption;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Tenant> tenant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HostelRoomCapacity> hostelRoomCapacity;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VisitorRequest> visitorRequest;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HostelVegDays> vegDays;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HostelFacilities> hostelFacilities;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VisitorRequest> visitorRequests;
	
	@OneToMany(mappedBy = "hostel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MealSkipRequest> mealSkipRequests;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", length = 29, nullable = false, updatable = false)
	private Date dateCreated;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_updated")
	private Date dateUpdated;

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

	public Integer getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public FoodOptions getFoodOption() {
		return foodOption;
	}

	public void setFoodOption(FoodOptions foodOption) {
		this.foodOption = foodOption;
	}

	public List<Tenant> getTenant() {
		return tenant;
	}

	public void setTenant(List<Tenant> tenant) {
		this.tenant = tenant;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<HostelRoomCapacity> getHostelRoomCapacity() {
		return hostelRoomCapacity;
	}

	public void setHostelRoomCapacity(List<HostelRoomCapacity> hostelRoomCapacity) {
		this.hostelRoomCapacity = hostelRoomCapacity;
	}

	public List<VisitorRequest> getVisitorRequests() {
		return visitorRequests;
	}

	public void setVisitorRequests(List<VisitorRequest> visitorRequests) {
		this.visitorRequests = visitorRequests;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
