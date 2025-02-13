package com.sapple.tot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.HostelFacilities;

public interface HostelFacilitiesRepository extends JpaRepository<HostelFacilities, Long> {
	@Query("from HostelFacilities where hostel.id = ?1")
	List<HostelFacilities> getHostelFacilities(Long hostelId);
	
	@Query("select hf.facility.id from HostelFacilities hf where hf.hostel.id = ?1")
	List<Long> getFacilitiesIds(Long hostelId);
	
	@Modifying
	@Query("delete from HostelFacilities where hostel.id = ?1")
	void deletePreviousEntriesForHostel(Long hostelId);
}
