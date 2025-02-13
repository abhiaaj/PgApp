package com.sapple.tot.helperDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VisitorRequestDataForOwnerDto {
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")// HH:mm:ss", timezone="IST")
	private Date entryTime;
	@JsonFormat(pattern = "yyyy-MM-dd")// HH:mm:ss", timezone="IST")
	private Date exitTime;
	private String visitorName;
	private String tenantName;
	private String mobileNo;
	private Integer overnightStay;
	private String reasonOfVisit;
	private String otpStatus;
	private Integer smsStatus;
	private String requestStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Integer getOvernightStay() {
		return overnightStay;
	}
	public void setOvernightStay(Integer overnightStay) {
		this.overnightStay = overnightStay;
	}
	public String getReasonOfVisit() {
		return reasonOfVisit;
	}
	public void setReasonOfVisit(String reasonOfVisit) {
		this.reasonOfVisit = reasonOfVisit;
	}
	public String getOtpStatus() {
		return otpStatus;
	}
	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}
	public Integer getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(Integer smsStatus) {
		this.smsStatus = smsStatus;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
}
