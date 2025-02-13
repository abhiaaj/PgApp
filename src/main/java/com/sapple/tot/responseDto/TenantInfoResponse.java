package com.sapple.tot.responseDto;

import java.util.List;

public class TenantInfoResponse {
	private Integer dataSetIdentifier;

	// personal info -- dataSetIdentifier = 1
	private String firstName;
	private String middleName;
	private String lastName;

	// parental info -- dataSetIdentifier = 2
	private String parentFirstName;
	private String parentMiddleName;
	private String parentLastName;
	private String parentAddressLine1;
	private String parentAddressLine2;
	private String parentAddressLine3;
	private String parentContactNo;

	// local guardian info -- dataSetIdentifier = 3
	private String lgFirstName;
	private String lgMiddleName;
	private String lgLastName;
	private String lgAddressLine1;
	private String lgAddressLine2;
	private String lgAddressLine3;
	private String lgContactNo;

	// miscellaneous info -- dataSetIdentifier = 4
	private Long foodPreference;
	private Integer roomNo;
	private List<Long> facilities;

	// 1= student, 2= working
	private Integer tenantType;
	// institute info -- dataSetIdentifier = 5
	private String instituteName;
	private String instituteAddressLine1;
	private String instituteAddressLine2;
	private String instituteAddressLine3;
	// work-place info -- dataSetIdentifier = 5
	private String workPlaceName;
	private String workPlaceAddressLine1;
	private String workPlaceAddressLine2;
	private String workPlaceAddressLine3;

	// other (documents/images) info
	// TO-DO

	public Integer getDataSetIdentifier() {
		return dataSetIdentifier;
	}

	public void setDataSetIdentifier(Integer dataSetIdentifier) {
		this.dataSetIdentifier = dataSetIdentifier;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getParentFirstName() {
		return parentFirstName;
	}

	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

	public String getParentLastName() {
		return parentLastName;
	}

	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}

	public String getParentAddressLine1() {
		return parentAddressLine1;
	}

	public void setParentAddressLine1(String parentAddressLine1) {
		this.parentAddressLine1 = parentAddressLine1;
	}

	public String getParentAddressLine2() {
		return parentAddressLine2;
	}

	public void setParentAddressLine2(String parentAddressLine2) {
		this.parentAddressLine2 = parentAddressLine2;
	}

	public String getParentAddressLine3() {
		return parentAddressLine3;
	}

	public void setParentAddressLine3(String parentAddressLine3) {
		this.parentAddressLine3 = parentAddressLine3;
	}

	public String getParentContactNo() {
		return parentContactNo;
	}

	public void setParentContactNo(String parentContactNo) {
		this.parentContactNo = parentContactNo;
	}

	public String getLgFirstName() {
		return lgFirstName;
	}

	public void setLgFirstName(String lgFirstName) {
		this.lgFirstName = lgFirstName;
	}

	public String getLgMiddleName() {
		return lgMiddleName;
	}

	public void setLgMiddleName(String lgMiddleName) {
		this.lgMiddleName = lgMiddleName;
	}

	public String getLgLastName() {
		return lgLastName;
	}

	public void setLgLastName(String lgLastName) {
		this.lgLastName = lgLastName;
	}

	public String getLgAddressLine1() {
		return lgAddressLine1;
	}

	public void setLgAddressLine1(String lgAddressLine1) {
		this.lgAddressLine1 = lgAddressLine1;
	}

	public String getLgAddressLine2() {
		return lgAddressLine2;
	}

	public void setLgAddressLine2(String lgAddressLine2) {
		this.lgAddressLine2 = lgAddressLine2;
	}

	public String getLgAddressLine3() {
		return lgAddressLine3;
	}

	public void setLgAddressLine3(String lgAddressLine3) {
		this.lgAddressLine3 = lgAddressLine3;
	}

	public String getLgContactNo() {
		return lgContactNo;
	}

	public void setLgContactNo(String lgContactNo) {
		this.lgContactNo = lgContactNo;
	}

	public Long getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(Long foodPreference) {
		this.foodPreference = foodPreference;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	
	public List<Long> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Long> facilities) {
		this.facilities = facilities;
	}

	public Integer getTenantType() {
		return tenantType;
	}
	
	public void setTenantType(Integer tenantType) {
		this.tenantType = tenantType;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getInstituteAddressLine1() {
		return instituteAddressLine1;
	}

	public void setInstituteAddressLine1(String instituteAddressLine1) {
		this.instituteAddressLine1 = instituteAddressLine1;
	}

	public String getInstituteAddressLine2() {
		return instituteAddressLine2;
	}

	public void setInstituteAddressLine2(String instituteAddressLine2) {
		this.instituteAddressLine2 = instituteAddressLine2;
	}

	public String getInstituteAddressLine3() {
		return instituteAddressLine3;
	}

	public void setInstituteAddressLine3(String instituteAddressLine3) {
		this.instituteAddressLine3 = instituteAddressLine3;
	}

	public String getWorkPlaceName() {
		return workPlaceName;
	}

	public void setWorkPlaceName(String workPlaceName) {
		this.workPlaceName = workPlaceName;
	}

	public String getWorkPlaceAddressLine1() {
		return workPlaceAddressLine1;
	}

	public void setWorkPlaceAddressLine1(String workPlaceAddressLine1) {
		this.workPlaceAddressLine1 = workPlaceAddressLine1;
	}

	public String getWorkPlaceAddressLine2() {
		return workPlaceAddressLine2;
	}

	public void setWorkPlaceAddressLine2(String workPlaceAddressLine2) {
		this.workPlaceAddressLine2 = workPlaceAddressLine2;
	}

	public String getWorkPlaceAddressLine3() {
		return workPlaceAddressLine3;
	}

	public void setWorkPlaceAddressLine3(String workPlaceAddressLine3) {
		this.workPlaceAddressLine3 = workPlaceAddressLine3;
	}
}
