package com.sapple.tot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapple.tot.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value = "select * from user where contact_no = ?1", nativeQuery = true)
	public User findByContactNo(String contactNo);
}
