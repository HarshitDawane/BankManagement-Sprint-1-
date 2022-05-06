package com.capg.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.Exceptions.HandlingException;
import com.capg.Repository.AccountDetailsRepository;
import com.capg.Repository.CounterRepository;
import com.capg.Repository.UserDetailRepository;
import com.capg.entity.AccountDetails;
import com.capg.entity.Address;
import com.capg.entity.Counter;
import com.capg.entity.UserDetail;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class UserDetailController {

	
	@Autowired
    private UserDetailRepository userRepo;
	
	@Autowired
	private CounterRepository counterRepo ; 
	
	@Autowired 
	private AccountDetailsRepository accountDetailsRepository ; 
	
	AccountDetails accountDetails ; 

	  @PostMapping("/addCustomer") 
	  public Map<String,String> createUser(@RequestBody UserDetail user ) throws HandlingException {

	
	 String email = user.getEmail().trim();
	 String contact = user.getContact();
     String str = "";
     String password =  user.getPassword();
     
     String emailPattern="^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
     String mobilePattern="(^$|[6-9][0-9]{9})";
    // String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-])(?=\\S+$).{8, 20}$"; 
     
    
     //check password 
     
     boolean a;//validations lagane h 

     if(email.equals("") || contact.equals("")) {
         str = "Fields can not be empty";
         throw new HandlingException(str);
      
         
     }else if(!user.getEmail().matches(emailPattern)) {
         str = "Invalid email";
        
         throw new HandlingException(str);
     }else if(!user.getContact().toString().matches(mobilePattern)) {
         str = "Invalid contact number";
       
         throw new HandlingException(str);
     }
     else if(user.getPassword() == "" || user.getPassword().length() <8 ) {
         str = "Invalid Password ";
         throw new HandlingException(str);
     }
     else {
         int info = userRepo.countByContactOrEmail(user.getContact(), user.getEmail());

         if(info > 0) {
             System.out.println("Please Login to continue");
             str = "You are already registered .Please Login to continue";
             
             throw new HandlingException(str);
         }else {
             UserDetail inf = userRepo.save(user);
             str = "Entered";
             
             Map<String,String> map=new HashMap<String, String>();
		     map.put("Status Code", "202");
		     map.put("message", "Details Entered");
		     
			  return map ; 
            
         }
     } 
}
//
//  

     
//     
     @PostMapping("/createCurrentAccount")
     public String  createSavingAccount(@RequestBody UserDetail user) throws HandlingException{
    	 
    	 
    	 String email = user.getEmail();
    	 String contactNumber = user.getContact();
    	 AccountDetails accountDetailsCurrent =new AccountDetails();
    	 
    	   UserDetail info =  userRepo.findByContact(user.getContact());
    	   System.out.println(info);
    	   if(info !=null && email !=null )
    	   {
    		   if(info.getAadharCardNumber() !=null || info.getAddress() !=null)
    		   {
    			   int base = 96; //saving account starts from 96
    		         int bankCode = 701;
    		         String name = "CurrentAccountCounter";

    		         String contact = new String("9098143939");
    		         String accountType ="currentAccount";

    		         Counter counter =  counterRepo.findByName(name);
    		       

    		         if(counter != null) {
    		             int checkForCurrentAccount =  accountDetailsRepository.countByContactAndAccountType(contact, accountType);
    		             if(checkForCurrentAccount == 0) {
    		                 int transactionalCount = counter.getCount();
    		                 int inc = counterRepo.incrementSavingAccountCounter();

    		                 String transactioalID = ""+base+transactionalCount+bankCode;
    		                 if(inc == 1) {
    		                     System.out.println("Updated Successfully");
    		                     
    		                     accountDetailsCurrent.setAccountBalance(0.0);
    		                     accountDetailsCurrent.setAccountNumber(transactioalID);
    		                     accountDetailsCurrent.setAccountType("current");
    		                     accountDetailsCurrent.setContact(contactNumber);
    		                    accountDetailsRepository.save(accountDetailsCurrent);
    		                     return "Current Account Created ";
    		                 }else {
    		                     System.out.println("Incremnt Failed");
    		                     return "failed";
    		                 }
    		             }else {
    		                 return "Already Present";
    		             }
    		         }else {
    		             return "Invalid";
    		         }
    		   } else
        	   {
        		   throw new HandlingException("Aadhar card number doesn't exists"); 
        	   }
    	   }
    	   else
    	   {
    		   throw new HandlingException("Contact number doesn't exists"); 
    	   }
    	  
         //Account Number = Base+Counter+BankCode
         
         


     }
	
     @PostMapping("/createSavingAccount")
    public String  createCurrentAccount(@RequestBody UserDetail user) throws HandlingException{

    	 String email = user.getEmail();
    	 String contactNumber = user.getContact();
    	 AccountDetails accountDetailsSaving =new AccountDetails(); 
    	 
    	 UserDetail info =  userRepo.findByContact(user.getContact());
  	   if(info !=null && email !=null )
  	   {
  		 if(info.getAadharCardNumber() !=null || info.getAddress() !=null)
		   {
			  
        int base = 80; //saving account starts from 80
        int bankCode = 701;
        String name = "SavingAccountCounter";

        String contact = new String("9098143939");
        String accountType ="savingAccount";

        Counter counter = counterRepo.findByName(name);
        if(counter != null) {
            int checkForCurrentAccount =  accountDetailsRepository.countByContactAndAccountType(contact, accountType);
            if(checkForCurrentAccount == 0) {
                int transactionalCount = counter.getCount();
                int inc = counterRepo.incrementCurrentAccountCounter();

                String transactioalID = ""+base+transactionalCount+bankCode;
                if(inc == 1) {
                    System.out.println("Updated Successfully");
                    accountDetailsSaving.setAccountBalance(0.0);
                    accountDetailsSaving.setAccountNumber(transactioalID);
                    accountDetailsSaving.setAccountType("saving");
                    accountDetailsSaving.setContact(contactNumber);
                    accountDetailsRepository.save(accountDetailsSaving);
                    return "Saving Account created ";
                }else {
                    System.out.println("Increment Failed");
                    return "Failed";
                }
            }else {
                return "Already Present";
            }

        }else {
            return "Invalid";
        }

    }
  		else
   	   {
   		 throw new HandlingException("Contact number doesn't exists");   
   	   }
  }
  	   else
  	   {
  		 throw new HandlingException("Contact number doesn't exists");   
  	   }
 }
     
     @PostMapping("/submitDetail")
     public ResponseEntity<UserDetail>  updateUser(@RequestBody UserDetail user) throws HandlingException{


         String firstName = user.getFirstName().trim();
         String lastName = user.getLastName().trim();
         String middleName = user.getMiddleName().trim();
         String aadharCardNumber = user.getAadharCardNumber().trim();
         Address address = user.getAddress();
         UserDetail info =  userRepo.findByContact(user.getContact());
         
         if(info!=null)
         {
         info.setFirstName(firstName);
         info.setLastName(lastName);
         info.setMiddleName(middleName);
         info.setAddress(address);
         info.setAadharCardNumber(aadharCardNumber);
         UserDetail inf = userRepo.save(info);
         System.out.println(inf.getContact());
         return new ResponseEntity<UserDetail>((UserDetail) inf,HttpStatus.ACCEPTED);
         }
         else
         {
        	throw new HandlingException("Contact number doesn't exists"); 
         }



       


         }
	
}
