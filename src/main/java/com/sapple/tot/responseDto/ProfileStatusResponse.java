package com.sapple.tot.responseDto;

import java.util.List;

public class ProfileStatusResponse {
	private Integer personalInfoFlag;
	private Integer parentInfoFlag;
	private Integer localGuardianFlag;
	private Integer professionFlag;
	private Integer miscFlag;
	private Integer otherFlag;
	private Long hostelId;
	private String hostelName;
	private Integer mealRequests;
	private Integer meetingVisitorRequests;
	private Integer overnightVisitorRequests;
	private Integer visitorRequestUpdated;
	
	private List<Long> availableFacilityList;
	public Integer getPersonalInfoFlag() {
		return personalInfoFlag;
	}
	public void setPersonalInfoFlag(Integer personalInfoFlag) {
		this.personalInfoFlag = personalInfoFlag;
	}
	public Integer getParentInfoFlag() {
		return parentInfoFlag;
	}
	public void setParentInfoFlag(Integer parentInfoFlag) {
		this.parentInfoFlag = parentInfoFlag;
	}
	public Integer getLocalGuardianFlag() {
		return localGuardianFlag;
	}
	public void setLocalGuardianFlag(Integer localGuardianFlag) {
		this.localGuardianFlag = localGuardianFlag;
	}
	public Integer getProfessionFlag() {
		return professionFlag;
	}
	public void setProfessionFlag(Integer professionFlag) {
		this.professionFlag = professionFlag;
	}
	public Integer getMiscFlag() {
		return miscFlag;
	}
	public void setMiscFlag(Integer miscFlag) {
		this.miscFlag = miscFlag;
	}
	public Integer getOtherFlag() {
		return otherFlag;
	}
	public void setOtherFlag(Integer otherFlag) {
		this.otherFlag = otherFlag;
	}
	public List<Long> getAvailableFacilityList() {
		return availableFacilityList;
	}
	public void setAvailableFacilityList(List<Long> availableFacilityList) {
		this.availableFacilityList = availableFacilityList;
	}
	public Long getHostelId() {
		return hostelId;
	}
	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public Integer getMealRequests() {
		return mealRequests;
	}
	public void setMealRequests(Integer mealRequests) {
		this.mealRequests = mealRequests;
	}
	public Integer getMeetingVisitorRequests() {
		return meetingVisitorRequests;
	}
	public void setMeetingVisitorRequests(Integer meetingVisitorRequests) {
		this.meetingVisitorRequests = meetingVisitorRequests;
	}
	public Integer getOvernightVisitorRequests() {
		return overnightVisitorRequests;
	}
	public void setOvernightVisitorRequests(Integer overnightVisitorRequests) {
		this.overnightVisitorRequests = overnightVisitorRequests;
	}
	public Integer getVisitorRequestUpdated() {
		return visitorRequestUpdated;
	}
	public void setVisitorRequestUpdated(Integer visitorRequestUpdated) {
		this.visitorRequestUpdated = visitorRequestUpdated;
	}
}
