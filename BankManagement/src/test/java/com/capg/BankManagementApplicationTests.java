package com.capg;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.Repository.AccountDetailsRepository;
import com.capg.Repository.ContactUsRepository;
import com.capg.Repository.DepositRepository;
import com.capg.Repository.TransactionRepository;
import com.capg.controller.TransactionController;
import com.capg.entity.AccountDetails;
import com.capg.entity.ContactUs;
import com.capg.entity.Deposit;
import com.capg.entity.Transaction;

@SpringBootTest
class BankManagementApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Autowired
	private TransactionRepository transactionRepository ; 
	
	private static TransactionController transactionController ; 
	
	@Autowired
	private AccountDetailsRepository accountRepository ; 
	
	@Autowired
	private ContactUsRepository repo;
	
	@Autowired 
	private DepositRepository depositRepository ; 
	
	
	
	private static Transaction t;
	private static AccountDetails accFirst , accSecond ; 
	
	//checking data to be saved in contact us 
	@Test
	public void saveData()
	{
		repo.save(new ContactUs(123,"Kripansha","kripa@gmail.com","this is kripa msg","1234567890"));
		ContactUs con=repo.findByName("Kripansha");
		assertThat(con.getName()).isEqualTo("Kripansha");
	}
	
	
	
	//creating objects here
	@BeforeAll
	public static  void init()
	{
		t= new Transaction();
		accFirst = new AccountDetails();
		accSecond = new AccountDetails();
		transactionController = new TransactionController();
	}
	
	// free objects here
	@AfterAll
	public static void destroy()
	{
		t= null ;
		accFirst = null ; 
		accSecond = null ; 
	}

	
    // account created and stored in db 
	@Test 
	public void createAccountFirst()
	{
		
		accFirst.setId(1);
	    accFirst.setAccountBalance(3000);
		accFirst.setAccountNumber("101");
		accFirst.setAccountType("current");
		accFirst.setContact("123");
		accountRepository.save(accFirst);
		assertNotNull(accountRepository.findByAccountNumber("101"));
	}
	
	
	@Test 
	public void findAccountSecond()
	{
	
		accSecond = accountRepository.findByAccountNumber("961003701");
		
		assertEquals(1,accSecond.getAccountBalance());
	}
	
	//creating tranaction from first to second 
	@Test
	public void createTransaction()
	{
		if(accFirst.getAccountBalance()>=100)
		{
			t.setTransactionId("1");
			t.setTransactionAmount(100);
			t.setTransactionFrom("961003702");
			t.setTransactionTo("961003705");
			t.setTransactionFromType("saving");
			t.setTransactionToType("saving");
			transactionRepository.save(t);
			assertNotNull(transactionRepository.findById(1));
		}
		else
		{
			assertEquals("Not possible", "Not possible");
		}
		
	
	}
	
	//created deposit 
	public void createDeposit()
	{
		double amt = accFirst.getAccountBalance() + 100 ; 
		accFirst.setAccountBalance(amt);
		depositRepository.save(new Deposit(1 , "04-04-2022","",100,"current","EmpID"));
		Deposit d = depositRepository.findByAccType("current");
		assertEquals(d.getAccType(), "current");
	}
}
