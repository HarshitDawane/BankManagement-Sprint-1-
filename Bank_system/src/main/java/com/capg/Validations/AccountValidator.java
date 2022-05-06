package com.capg.Validations;

import java.math.BigInteger;

public class AccountValidator {
 

    public boolean validateType(String type1 , String type2) {
    	if((type1.equalsIgnoreCase("saving") || type1.equalsIgnoreCase("current")) && (type2.equalsIgnoreCase("saving") || type2.equalsIgnoreCase("current")))
        return true;
        else
        	return false ; 
    }
    public boolean validateTypeSingle(String type1 ) {
    	if((type1.equalsIgnoreCase("saving") || type1.equalsIgnoreCase("current")))
        return true;
        else
        	return false ; 
    }
    
    public boolean validateAmount(Double amount) {
    	if(!Double.isNaN(amount))
		   {
			   if(amount >0)
				 return true ; 
			   else 
				  return false ; 
		   }
		 else 
			return false ; 
    }
    
    public boolean validateAccountNumber(String  accountNumberFrom , String accountNumberTo) {
        return true ;
    }
    public boolean validateAccountNumberSingle(String  accountNumber) {
        return true ;
    }
  
}
