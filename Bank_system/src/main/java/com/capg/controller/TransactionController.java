package com.capg.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capg.Exceptions.HandlingException;
import com.capg.Repository.AccountDetailsRepository;
import com.capg.Repository.CounterRepository;
import com.capg.Repository.TransactionRepository;
import com.capg.Validations.AccountValidator;
import com.capg.entity.AccountDetails;
import com.capg.entity.Counter;
import com.capg.entity.Transaction;
import com.capg.services.TransactionService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/transfer")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountDetailsRepository accountRepository;

	private AccountValidator accountValidator;
	
	@Autowired
 	private CounterRepository counterRepo;
	
	@Autowired
	private TransactionService transService ; 
	
	AccountDetails a , b ;
	
	double updatedBalanceA , updatedBalanceB ,amount ; 

	LocalDateTime instant;

	Transaction tran;
	
	

	@PostMapping("/detail")
	   public ResponseEntity<Transaction>  transferAmt(@RequestBody Transaction transaction  )throws HandlingException{
		   
		String transactionFrom = transaction.getTransactionFrom() ; 
		String transactionTo = transaction.getTransactionTo() ; 
		   String transactionFromType = transaction.getTransactionFromType();
		   String transactionToType = transaction.getTransactionToType();
		   amount = transaction.getTransactionAmount() ; 
            
		   accountValidator = new AccountValidator();
	        
    if(accountValidator.validateType(transactionFromType , transactionToType) && accountValidator.validateAmount(amount) && accountValidator.validateAccountNumber(transactionFrom, transactionTo))  
    {
    	  a = accountRepository.findByAccountNumber(transactionFrom);
          b = accountRepository.findByAccountNumber(transactionTo);
          
        if(a!=null && b!=null) 
         {
        	   
           if(a.getAccountType().equalsIgnoreCase(transactionFromType) && b.getAccountType().equalsIgnoreCase(transactionToType))
            {		
	           if(amount <= a.getAccountBalance())
				{
	        	  instant = LocalDateTime.now();
	        	   updateDetails(a,b);
	        	  
	        	    String time = time(instant);
	        	   String transactionID = createTransactionId();
	        	    
	                transaction.setTransactionDate( time);
	          
	                transaction.setTransactionId(transactionID);
	      		
	               //  tran = transactionRepository.save(transaction);
	                
	                tran = transService.save(transaction);
					
				    return new ResponseEntity<Transaction>(tran, HttpStatus.CREATED);
				 }
	           
				 else 
				    throw new HandlingException("Balance not enough ");
			  }
           
	        else 
	            throw new HandlingException("The account Type doesn't match. Please check your details."); 
	       }
          else 
               throw new HandlingException("The account number does not exist. ");
    }
    else
       throw new HandlingException("Invalid input");
 }
	
	
	
	public void updateDetails(AccountDetails first , AccountDetails second)
	{
		updatedBalanceA = a.getAccountBalance() - amount;
		updatedBalanceB = b.getAccountBalance() + amount;
		
		first.setAccountBalance(updatedBalanceA);
		second.setAccountBalance(updatedBalanceB);
		
		accountRepository.save(first);
	    accountRepository.save(second);
	}
	
	
	
	 public String  createTransactionId() {
		 
		 String name = "TransactionCounter";
		
		 Counter counter = counterRepo.findByName(name);
		 if(counter != null) {
			 int transactionalCount = counter.getCount();
			 Integer check = counterRepo.incrementTransactionCounter();
			 
			 String transactioalID = "TRID"+transactionalCount+"IB";
			 if(check == 1) {
				 System.out.println("Created Successfully");
				 return transactioalID;
			 }else {
				 System.out.println("Did not incremented successfully");
				 return null;
			 }	  
		 }else {
			 return "Invalid";		 
		 }
}
	 
	 public String time(LocalDateTime l)
	 {
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 l = LocalDateTime.now();
		String currentTime =l.format(myFormatObj);
		return currentTime ; 
	 }

}
