package com.capg.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capg.entity.CustomerAppointment;

public interface CustomerAppointmentRepository extends CrudRepository<CustomerAppointment, Integer> {

	List<CustomerAppointment> findByDate(String date);
    List<CustomerAppointment> findAll();

   
}
