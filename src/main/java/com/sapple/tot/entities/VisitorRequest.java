package com.sapple.tot.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "visitor_requests")
public class VisitorRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "entry_time")
	private Date entryTime;
	
	@Column(name = "exit_time")
	private Date exitTime;
	
	@Column(name = "name")
	private String visitorName;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "reason_of_visit")
	private String reasonOfVisit;
	
	@Column(name = "overnight_stay")
	private Integer overnightStay;
	
	@Column(name = "hostel_food")
	private Integer hostelFood;
	
	@Column(name = "otp")
	private Integer otp;
	
	@Column(name = "otp_status")
	private Integer otpStatus;
	
	@Column(name = "sms_status")
	private Integer smsStatus;
	
	@Column(name = "date_approved")
	private Date dateApproved;
	
	@Column(name = "date_rejected")
	private Date dateRejected;
	
	@Column(name = "date_put_on_hold")
	private Date datePutOnHold;
	
	@Column(name = "is_Active")
	private Integer isActive;
	
	@Column(name = "status_updated")
	private Integer requestStatusUpdated;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visitor_address_proof_image_id")
	private Media media;
	
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hostel_id")
	private Hostel hostel;
	
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

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getReasonOfVisit() {
		return reasonOfVisit;
	}

	public void setReasonOfVisit(String reasonOfVisit) {
		this.reasonOfVisit = reasonOfVisit;
	}

	public Integer getOvernightStay() {
		return overnightStay;
	}

	public void setOvernightStay(Integer overnightStay) {
		this.overnightStay = overnightStay;
	}

	public Integer getHostelFood() {
		return hostelFood;
	}

	public void setHostelFood(Integer hostelFood) {
		this.hostelFood = hostelFood;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Integer getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(Integer otpStatus) {
		this.otpStatus = otpStatus;
	}

	public Integer getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(Integer smsStatus) {
		this.smsStatus = smsStatus;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}

	public Date getDateRejected() {
		return dateRejected;
	}

	public void setDateRejected(Date dateRejected) {
		this.dateRejected = dateRejected;
	}

	public Date getDatePutOnHold() {
		return datePutOnHold;
	}

	public void setDatePutOnHold(Date datePutOnHold) {
		this.datePutOnHold = datePutOnHold;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getRequestStatusUpdated() {
		return requestStatusUpdated;
	}

	public void setRequestStatusUpdated(Integer requestStatusUpdated) {
		this.requestStatusUpdated = requestStatusUpdated;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
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
