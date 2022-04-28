package com.capg.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.EmployeeDetails;



@Repository
public interface EmployeeDetailsRepository extends CrudRepository<EmployeeDetails,Integer> {
	
	public List<EmployeeDetails> findAll();
	

	
	public EmployeeDetails findByContact(String contact);
	
	public EmployeeDetails findByEmployeeIdAndPassword(String empId, String password);
	
	public EmployeeDetails findByEmployeeId(String empId);

}
