package com.capg.Repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capg.entity.Counter;

@Repository
public interface CounterRepository extends CrudRepository<Counter, Integer>{
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE counter AS t set count=t.count+1 WHERE name = 'SavingAccountCounter'", nativeQuery = true)
	public int incrementSavingAccountCounter();
	
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE counter AS t set count=t.count+1 WHERE name = 'CurrentAccountCounter'", nativeQuery = true)
	public int incrementCurrentAccountCounter();
	
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE counter AS t set count=t.count+1 WHERE name = 'TransactionCounter'", nativeQuery = true)
	public Integer incrementTransactionCounter();
	
	public Counter findByName(String name);
	
	 
    @Modifying
    @Transactional
    @Query(value ="UPDATE counter AS t set count=t.count+1 WHERE name = 'TokenGenerator'", nativeQuery = true)
    public int incrementToken();
    
    @Modifying
    @Transactional
    @Query(value ="UPDATE counter AS t set count=t.count+1 WHERE name = 'EmployeeId'", nativeQuery = true)
    public int incrementEmployeeId();
	
	
	

}