package com.capg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AccountDetailsId")
    private int Id;
    private String contact;
    private String accountNumber;
    private String accountType;
    private double accountBalance ;
    
    
	public AccountDetails() {
		super();
	}
	public AccountDetails(int id, String contact, String accountNumber, String accountType, double accountBalance) {
		super();
		Id = id;
		this.contact = contact;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
    
    
}
