package com.sapple.tot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
	@Query("SELECT O.id FROM Owner O WHERE O.user.id = ?1")
	public Long getOwnerByUserId(Long userId);
}
