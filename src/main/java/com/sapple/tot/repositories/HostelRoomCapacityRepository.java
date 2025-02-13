package com.sapple.tot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.HostelRoomCapacity;

public interface HostelRoomCapacityRepository extends JpaRepository<HostelRoomCapacity, Long> {
	@Query("from HostelRoomCapacity where hostel.id = ?1")
	List<HostelRoomCapacity> getHostelRoomCapacityList(Long hostelId);
	
	@Modifying
	@Query("delete from HostelRoomCapacity where hostel.id = ?1")
	void deletePreviousEntriesForHostel(Long hostelId);
}
