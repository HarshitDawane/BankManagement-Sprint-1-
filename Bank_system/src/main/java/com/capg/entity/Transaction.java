package com.capg.entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Transaction {

	
	   @Id
	  @Column(name = "Transaction_ID")
	  private String    transactionId ;
	   @Column(name = "Transfer_Amount")
	  private double  transactionAmount ;
	   @Column(name = "Transaction_Date")
	  private String  transactionDate ;  
	   
	   @Column(name = "Transaction_From")
	  private String transactionFrom ;
	   @Column(name = "Transaction_TO")
      private String transactionTo ;
	   @Column(name = "Transaction_From_Type")
      private String transactionFromType ; 
	   @Column(name = "Transaction_To_Type")
      private String transactionToType ; 
	   
	
      
      
      
	    public Transaction(String transactionId, double transactionAmount, String transactionDate, String transactionFrom,
	    		String transactionTo, String transactionFromType, String transactionToType) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.transactionFrom = transactionFrom;
		this.transactionTo = transactionTo;
		this.transactionFromType = transactionFromType;
		this.transactionToType = transactionToType;
		
	}
		public String getTransactionFromType() {
		return transactionFromType;
	}
	public void setTransactionFromType(String transactionFromType) {
		this.transactionFromType = transactionFromType;
	}
	public String getTransactionToType() {
		return transactionToType;
	}
	public void setTransactionToType(String transactionToType) {
		this.transactionToType = transactionToType;
	}
		public Transaction()
	      {
	    	 super(); 
	      }
	
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public double getTransactionAmount() {
			return transactionAmount;
		}
		public void setTransactionAmount(double transactionAmount) {
			this.transactionAmount = transactionAmount;
		}
		public String getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
		}
		public String getTransactionFrom() {
			return transactionFrom;
		}
		
		
		public void setTransactionFrom(String transactionFrom) {
			this.transactionFrom = transactionFrom;
		}
		public String getTransactionTo() {
			return transactionTo;
		}
		public void setTransactionTo(String transactionTo) {
			this.transactionTo = transactionTo;
	} 

      
}
