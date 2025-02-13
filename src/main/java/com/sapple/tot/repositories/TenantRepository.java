package com.sapple.tot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
	@Query("from Tenant t where t.user.id = ?1")
	Tenant getTenantByUserId(Long userId);
}
