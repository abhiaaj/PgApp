package com.sapple.tot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.HostelVegDays;

public interface HostelVegDaysRepository extends JpaRepository<HostelVegDays, Long> {
	@Query("select hvg.dayId from HostelVegDays hvg where hvg.hostel.id = ?1")
	List<Integer> getVegFoodDays(Long hostelId);
	
	@Modifying
	@Query("delete from HostelVegDays where hostel.id = ?1")
	void deletePreviousEntriesForHostel(Long hostelId);
}
