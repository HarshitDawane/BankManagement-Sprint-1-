package com.capg.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.Exceptions.HandlingException;
import com.capg.Repository.AccountDetailsRepository;
import com.capg.Repository.EmployeeDetailsRepository;
import com.capg.Validations.AccountValidator;
import com.capg.entity.AccountDetails;
import com.capg.entity.Deposit;
import com.capg.entity.EmployeeDetails;
import com.capg.entity.Withdraw;
import com.capg.services.DepositService;
import com.capg.services.WithdrawService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	
	  Withdraw withdraw ; 
	  Deposit deposit ; 
	
	
	
	  @Autowired
	   private AccountDetailsRepository accountRepository ; 
	  
	 
	  
	  @Autowired
	  private DepositService depositService ; 
	  
	  @Autowired 
	  private WithdrawService withdrawService ; 
	  
	
	  
	  @Autowired 
	  private EmployeeDetailsRepository employeeDetailsRepository ; 
	  

	  private AccountValidator accountValidator = new AccountValidator();
	
	  AccountDetails a, b;
	  double updatedBalance ,depositAmt , withdrawAmt ,updatedBalanceB;
	  LocalDateTime instantOfDeposit,instantOfWithdraw  ;
	  
	  
	 
	  
	  
	    @PostMapping("/EmployeeLogin")
	    public Map<String,String> employeeLogin(@RequestBody EmployeeDetails emp) {

	        String empId = emp.getEmployeeId().trim();
	        String password = emp.getPassword().trim();

	        String regex_password = "^[a-zA-Z0-9@$!%*#?&]{8,}$";

	        if(password.isEmpty() ) {
	            System.out.println("fields can not be empty");

	        }else {
	            if(password.matches(regex_password)) {
	                EmployeeDetails details = employeeDetailsRepository.findByEmployeeIdAndPassword(empId,password);

	                Map<String, String> map=new HashMap<String, String>();
	                if(details != null) {
	                    map.put("Status", "ACCETED");
	                    map.put("Message", "Login Successfull.");
	                }else {
	                    map.put("Status", "Forbidden");
	                    map.put("Message", "Invalid Credentials");
	                }

	                return map;
	            }else {
	                System.out.println("Invalid data");
	            }
	        }

	        return null;


	    }
	  
	  
	@PostMapping("/deposit")
	public ResponseEntity<Deposit>  depositAmt(@RequestBody Deposit d )throws HandlingException
	{
		
		
		String accNo = d.getAccNo() ; 
	    String accType = d.getAccType() ; 
	    double depositAmt = d.getDepositAmt() ; 
	    String depositBy = d.getDepositedBy() ; 
	    
	    if(accountValidator.validateTypeSingle(accType) && accountValidator.validateAmount(depositAmt) && accountValidator.validateAccountNumberSingle(accNo) ) 
	    {
	    	
	    	if(employeeDetailsRepository.findByEmployeeId(depositBy) !=null)
	    	{
	    	
	    	
	    	
	    	AccountDetails a = accountRepository.findByAccountNumber(accNo);
	    	if(a!=null) 
	    	{
		      if(a.getAccountType().equalsIgnoreCase(accType))
		      {
		    	 double  updatedBalance = a.getAccountBalance()+ depositAmt;
		    	  a.setAccountBalance(updatedBalance);
		    	  accountRepository.save(a);
		    	 
		    	  String currentTime = time(instantOfDeposit);
		    	  d.setDate(currentTime);
		    	 // deposit = depositRepository.save(d);
		    	  deposit = depositService.save(d);
		    	  return new ResponseEntity<Deposit>(deposit, HttpStatus.CREATED);
		      }
		      else 
		      {
		    	  throw new HandlingException("Account doesn't exists. Please Recheck your details.");
		      }
	    	  
	         }
		    else
		    {
		    	throw new HandlingException("Account doesn't exists");
		    }
	    }else 
	    {
	    	throw new HandlingException("Employee doesn't exists");
	    	
	    }
	    }
	    else 
	    {
	    	throw new HandlingException("Invalid input");
	    }
	}

	
	@PostMapping("/withdraw")
	public ResponseEntity<Withdraw>  withdrawAmt(@RequestBody Withdraw w ) throws  HandlingException
	{

		String accNo = w.getAccNo();
	    String accType = w.getAccType();
	    double withdrawAmt = w.getWithdrawAmt();
	    String withdrawBy = w.getWithdrawBy() ; 
	    
	    if(accountValidator.validateTypeSingle(accType) && accountValidator.validateAmount(withdrawAmt) && accountValidator.validateAccountNumberSingle(accNo)) 
	    {
	    	
	    	if(employeeDetailsRepository.findByEmployeeId(withdrawBy) !=null)
	    	{
	    	 b = accountRepository.findByAccountNumber(accNo);
	      if(b!=null)
	      {
	    	  if(b.getAccountType().equalsIgnoreCase(accType))
	    	  {
		    	  if(b.getAccountBalance()>=withdrawAmt) 
		    	  {
		    		 
		    		double  updatedBalance = b.getAccountBalance()- withdrawAmt;
		    		  b.setAccountBalance(updatedBalance);
		        	  accountRepository.save(b);
				         String currentTime =time(instantOfWithdraw);
				         w.setDate(currentTime);
				    	// withdraw = withdrawRepository.save(w);
				         withdraw = withdrawService.save(w);
				    	return new ResponseEntity<Withdraw>(withdraw, HttpStatus.CREATED);
		    	  }
		    	  else 
		    	      throw new HandlingException("Not enough Balance");
		    	  
	         }
	    	  else 
	                throw new HandlingException("Account doesn't exists. Please Recheck your details.");
	    	  
	      }
	      else 
	          throw new HandlingException("Account doesn't exists");
	      
	    }
	    	else
	    		throw new HandlingException("Employee doesn't exists");
	    }
	    else
	       throw new HandlingException("Input is invalid");
	    
		
	}
	
	 public String time(LocalDateTime l)
	 {
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 l = LocalDateTime.now();
		String currentTime =l.format(myFormatObj);
		return currentTime ; 
	 }
}
