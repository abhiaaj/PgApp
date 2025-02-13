package com.sapple.tot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.VisitorRequest;

public interface VisitorRequestsRepository extends JpaRepository<VisitorRequest, Long> {
	@Query("from VisitorRequest vr where vr.tenant.id = ?1")
	List<VisitorRequest> getVisitorRequestsForTenant(Long tenantId);
	
	@Query("from VisitorRequest vr where vr.hostel.id = ?1")
	List<VisitorRequest> getVisitorRequestsForOwner(Long hostelId);
	
	@Query(value = "select count(id) from visitor_requests where is_seen != 1 and overnight_stay = 0", nativeQuery = true)
	Integer getUnseenMeetingVisitorReq();
	
	@Query(value = "select count(id) from visitor_requests where is_seen != 1 and overnight_stay = 1", nativeQuery = true)
	Integer getUnseenOvernightVisitorReq();
	
	@Modifying
	@Query(value = "update visitor_requests set is_seen = 1 where id = ?1", nativeQuery = true)
	void updateSeenStatus(Long mealSkipReqId);
	
	@Query(value = "select count(id) from visitor_requests where status_updated = 1", nativeQuery = true)
	Integer getVisitorRequestStatusUpdated();
	
	@Modifying
	@Query(value = "update visitor_requests set status_updated = 2 where status_updated = 1", nativeQuery = true)
	void requestStatusFetchUpdated();
	
	@Query(value = "select * from visitor_requests where mobile_no = ?1", nativeQuery = true)
	VisitorRequest getRequestByContactNo(String contactNo);
	
	@Query(value = "select * from visitor_requests where mobile_no = ?1 and otp = ?2", nativeQuery = true)
	VisitorRequest verifyVisitorOtp(String contactNo, String otp);
}
