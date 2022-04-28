package com.capg.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.CounterDetails;



@Repository
public interface CounterDetailsRepository extends CrudRepository<CounterDetails, Integer> {
	List<CounterDetails> findByDate(String date);
	List<CounterDetails> findAll();
}
