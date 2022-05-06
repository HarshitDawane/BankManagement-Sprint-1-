package com.capg.Repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.AccountDetails;


@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetails, Integer>{

    public int countByContactAndAccountType(String contact, String accountType);
    AccountDetails findByAccountNumber(String id);
}