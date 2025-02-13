package com.sapple.tot.responseDto;

public class VisitiorOtpVerificationResponse {
	private String visitorName;
	private String tenantName;
	private String tenantMobileNo;
	private Integer overnightStay;
	private String visitorIdentityProofUrl;
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
	public String getTenantMobileNo() {
		return tenantMobileNo;
	}
	public void setTenantMobileNo(String tenantMobileNo) {
		this.tenantMobileNo = tenantMobileNo;
	}
	public Integer getOvernightStay() {
		return overnightStay;
	}
	public void setOvernightStay(Integer overnightStay) {
		this.overnightStay = overnightStay;
	}
	public String getVisitorIdentityProofUrl() {
		return visitorIdentityProofUrl;
	}
	public void setVisitorIdentityProofUrl(String visitorIdentityProofUrl) {
		this.visitorIdentityProofUrl = visitorIdentityProofUrl;
	}
}
