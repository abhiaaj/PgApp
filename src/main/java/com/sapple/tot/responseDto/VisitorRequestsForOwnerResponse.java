package com.sapple.tot.responseDto;

import java.util.List;

import com.sapple.tot.helperDto.VisitorRequestDataForOwnerDto;

public class VisitorRequestsForOwnerResponse {
	private List<VisitorRequestDataForOwnerDto> meetingVisitorList;
	private List<VisitorRequestDataForOwnerDto> pendingOvernightVisitorList;
	private List<VisitorRequestDataForOwnerDto> approvedOvernighVisitorList;
	public List<VisitorRequestDataForOwnerDto> getMeetingVisitorList() {
		return meetingVisitorList;
	}
	public void setMeetingVisitorList(List<VisitorRequestDataForOwnerDto> meetingVisitorList) {
		this.meetingVisitorList = meetingVisitorList;
	}
	public List<VisitorRequestDataForOwnerDto> getPendingOvernightVisitorList() {
		return pendingOvernightVisitorList;
	}
	public void setPendingOvernightVisitorList(List<VisitorRequestDataForOwnerDto> pendingOvernightVisitorList) {
		this.pendingOvernightVisitorList = pendingOvernightVisitorList;
	}
	public List<VisitorRequestDataForOwnerDto> getApprovedOvernighVisitorList() {
		return approvedOvernighVisitorList;
	}
	public void setApprovedOvernighVisitorList(List<VisitorRequestDataForOwnerDto> approvedOvernighVisitorList) {
		this.approvedOvernighVisitorList = approvedOvernighVisitorList;
	}
	
}
