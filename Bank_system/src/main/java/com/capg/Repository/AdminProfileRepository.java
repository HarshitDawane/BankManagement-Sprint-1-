package com.capg.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.entity.AdminProfile;



@Repository
public interface AdminProfileRepository extends CrudRepository<AdminProfile, Integer> {
	
	public AdminProfile findByName(String str);		
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE admin_profile_details SET password = :password WHERE name = :name", nativeQuery = true)
	public int editByNameAndPassword(@Param("name")String username, @Param("password") String password);
	
	public AdminProfile findByEmailAndPassword(String email, String Password);
}
