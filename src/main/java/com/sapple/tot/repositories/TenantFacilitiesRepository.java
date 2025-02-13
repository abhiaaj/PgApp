package com.sapple.tot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.TenantFacilities;

public interface TenantFacilitiesRepository extends JpaRepository<TenantFacilities, Long> {
	@Query("select tf.facility.id from TenantFacilities tf where tf.tenant.id = ?1")
	List<Long> getTenantFacilities(Long tenantId);
	
	@Modifying
	@Query("delete from TenantFacilities tf where tf.tenant.id = ?1")
	void deletePreviousEntries(Long tenantId);
}
