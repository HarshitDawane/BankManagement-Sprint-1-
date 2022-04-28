package com.capg.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.UserDetail;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Integer> {

	public UserDetail findByEmailAndPassword(String email,String password);

    public int countByContactOrEmail(String contact,String email);

    public UserDetail findByContact(String contact);
    
//    @Query("select f,a from UserDetails AS f INNER JOIN Address AS a ON f.contact=a.contact")
//    public List<UserDetail> findAll();

    // isko edit karna h abhi 
//    @Query("select f.firstName as namw ,a.city as city from UserDetails AS f INNER JOIN Address AS a ON f.contact=a.contact")
//    public Iterable<UserDetail> findAll();
}
