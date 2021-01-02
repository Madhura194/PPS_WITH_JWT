package com.cg.pps.dataaccess.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.pps.dataaccess.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
	public UserEntity findByUsername(String username);
	
	
	 
}
