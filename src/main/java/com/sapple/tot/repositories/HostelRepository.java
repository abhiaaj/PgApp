package com.sapple.tot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.Hostel;

public interface HostelRepository extends JpaRepository<Hostel, Long> {
	@Query("from Hostel where owner.id = ?1")
	List<Hostel> getHostelsByOwner(Long ownerId);
}
