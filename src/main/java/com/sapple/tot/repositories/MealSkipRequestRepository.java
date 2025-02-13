package com.sapple.tot.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.MealSkipRequest;

public interface MealSkipRequestRepository extends JpaRepository<MealSkipRequest, Long> {
	@Query(value = "select * from meal_requests where tenant_id = ?1 and (date(date) = date(now()) or datediff(date(date), date(now())) = 1)", nativeQuery = true)
	List<MealSkipRequest> getMealSkipDataForTenantForTwoDays(Long tenantId);
	
	@Query(value = "select * from meal_requests where tenant_id = ?1 and date(date) = date(?2)", nativeQuery = true)
	List<MealSkipRequest> getMealSkipDataForTenantForSpecificDay(Long tenantId, Date date);
	
	@Query(value = "select * from meal_requests where hostel_id = ?1 and date(date) = date(now())", nativeQuery = true)
	List<MealSkipRequest> getMealSkipDataForOwnerForCurrentDay(Long hostelId);
	
	@Query(value = "select * from meal_requests where hostel_id = ?1 and date(date) = date(?2)", nativeQuery = true)
	List<MealSkipRequest> getMealSkipDataForOwnerForSpecificDay(Long hostelId, Date date);
	
	@Modifying
	@Query(value = "delete from meal_requests where tenant_id = ?1 and date(date) = date(?2)", nativeQuery = true)
	void deleteForSpecificDateAndTenant(Long tenantId, Date date);
	
	@Query(value = "select count(id) from meal_requests where is_seen != 1", nativeQuery = true)
	Integer getUnseenRequests();
	
	@Modifying
	@Query(value = "update meal_requests set is_seen = 1 where id = ?1", nativeQuery = true)
	void updateSeenStatus(Long mealSkipReqId);
}
