package com.capg.Repository;

import org.springframework.data.repository.CrudRepository;

import com.capg.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
