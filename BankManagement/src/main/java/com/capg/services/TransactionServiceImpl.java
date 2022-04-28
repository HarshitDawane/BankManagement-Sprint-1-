package com.capg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.Repository.TransactionRepository;
import com.capg.entity.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository ;
	
	@Override
	public Transaction save(Transaction transaction) {
		
		transactionRepository.save(transaction);
		return transaction;
	}

}
