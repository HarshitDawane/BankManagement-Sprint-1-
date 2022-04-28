package com.capg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.Repository.DepositRepository;
import com.capg.entity.Deposit;

@Service
public class DepositServiceImpl implements DepositService{

	@Autowired
	private DepositRepository depositRepository ; 
	
	@Override
	public Deposit save(Deposit deposit) {
		
		return deposit;
	}

}
