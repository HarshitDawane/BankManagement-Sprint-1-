package com.capg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.Repository.WithdrawRepository;
import com.capg.entity.Withdraw;

@Service
public class WithdrawServiceImpl implements WithdrawService {
	 
	@Autowired 
	  private WithdrawRepository withdrawRepository ; 
	
	
	@Override
	public Withdraw save(Withdraw with) {
	 
		withdrawRepository.save(with);
		return with;
	}

}
