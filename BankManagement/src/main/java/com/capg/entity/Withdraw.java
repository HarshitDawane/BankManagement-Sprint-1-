package com.capg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Withdraw {

	@Id   
	@Column(name = "Withdraw_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	
	@Column(name = "Withdraw_Date")
	private String date ;
	
	@Column(name = "Account_Number")
	private String accNo ;
	
	@Column(name = "Withdraw_Amount")
	private double withdrawAmt ;
	
	@Column(name = "Account_Type ")
	private String accType ;
	
	private String withdrawBy ; 
	public Withdraw(int id, String date, String accNo, double withdrawAmt, String accType, String withdrawBy) {
		super();
		this.id = id;
		this.date = date;
		this.accNo = accNo;
		this.withdrawAmt = withdrawAmt;
		this.accType = accType;
		this.withdrawBy = withdrawBy;
	}

	public String getWithdrawBy() {
		return withdrawBy;
	}

	public void setWithdrawBy(String withdrawBy) {
		this.withdrawBy = withdrawBy;
	}

	public Withdraw() {
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
	public double getWithdrawAmt() {
		return withdrawAmt;
	}
	public void setWithdrawAmt(double withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}

	
}
