package com.capg.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.ContactUs;

@Repository
public interface ContactUsRepository extends CrudRepository<ContactUs, Integer> {

	ContactUs findByName(String name);
    List<ContactUs> findAll();
}
