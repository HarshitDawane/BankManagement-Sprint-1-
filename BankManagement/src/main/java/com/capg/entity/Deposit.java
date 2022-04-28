package com.capg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Deposit {

	@Id   @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "Id")
	private int id ;
	
	  @Column(name = "Deposit_Time")
	private String date ;
	  @Column(name = "Account_Number")
	private String accNo ;
	  @Column(name = "Deposit_Amount")
	private double depositAmt ;
	  @Column(name = "Account_Type")
	private String accType ;
	
	  private String depositedBy ; 
	
	
	public Deposit(int id, String date, String accNo, double depositAmt, String accType, String depositedBy) {
		super();
		this.id = id;
		this.date = date;
		this.accNo = accNo;
		this.depositAmt = depositAmt;
		this.accType = accType;
		this.depositedBy = depositedBy;
	}
	public String getDepositedBy() {
		return depositedBy;
	}
	public void setDepositedBy(String depositedBy) {
		this.depositedBy = depositedBy;
	}
	public Deposit() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public double getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(double depositAmt) {
		this.depositAmt = depositAmt;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}

	
	
}
