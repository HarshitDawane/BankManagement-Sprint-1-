package com.capg.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.Exceptions.HandlingException;
import com.capg.Repository.AdminProfileRepository;
import com.capg.Repository.ContactUsRepository;
import com.capg.Repository.CounterDetailsRepository;
import com.capg.Repository.CounterRepository;
import com.capg.Repository.CustomerAppointmentRepository;
import com.capg.Repository.EmployeeDetailsRepository;
import com.capg.Repository.UserDetailRepository;
import com.capg.entity.AdminProfile;
import com.capg.entity.ContactUs;
import com.capg.entity.CounterDetails;
import com.capg.entity.CustomerAppointment;
import com.capg.entity.EmployeeDetails;
import com.capg.entity.UserDetail;
import com.capg.entity.reponseDetails;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminProfileRepository adminProfileRepo;
	
	@Autowired
	private EmployeeDetailsRepository  employeeDetailsRepo;
	
	@Autowired
	private CounterRepository  counterRepo;
	
    @Autowired
    private CustomerAppointmentRepository customerAppointmentRepository ; 
	
	@Autowired
	private CounterDetailsRepository  counterDetailsRepo;
	
	@Autowired
	private UserDetailRepository userDetailRepository ; 
	
	@Autowired
	private ContactUsRepository contactUsRepository ; 
	
	
	@PostMapping("/profile")
	public ResponseEntity<AdminProfile> createAdminProfile() {
		AdminProfile details = adminProfileRepo.findByName("Admin");
		return new ResponseEntity<AdminProfile>( details,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/profileEdit")
	@ResponseBody
	public String editPassword(@RequestParam(value = "oldPassword") String oldPassword,@RequestParam(value = "newPassword") String newPassword,@RequestParam(value = "confirmPassword") String confirmPassword) throws HandlingException {
					

		String regex_password = "^[a-zA-Z0-9@$!%*#?&]{8,}$";
		
		if(!oldPassword.isEmpty() || !newPassword.isEmpty() || !confirmPassword.isEmpty()) {
			if(oldPassword.matches(regex_password) && newPassword.matches(regex_password) && confirmPassword.matches(regex_password) ) {					
				
			if(confirmPassword.equals(newPassword)) {
				
				AdminProfile userData = adminProfileRepo.findByName("Admin");
				System.out.println(userData);
				
				if(userData != null) {
					if(userData.getPassword().equals(oldPassword)) {
						
						int info = adminProfileRepo.editByNameAndPassword("Admin", newPassword);
						if(info > 0) {
							return "updated";
						}else {
							return "failed";
						}
						
					}else {
						System.out.println("Incorrect Old Password");
						throw new HandlingException("Incorrect Old Password");
					}	
				}else {
					System.out.println("Invalid Input");
					throw new HandlingException("Invalid Input");
				}			
		
			}else {
				System.out.println("Password do not match!");
				throw new HandlingException("Password do not match!");
			}
		}else {
			System.out.println("Invalid Password format.");
			throw new HandlingException("Invalid Password format.");
		}
	
	}else {
		System.out.println("Fields can not be empty.");
		throw new HandlingException("Fields can not be empty.");
	}
	
//	return oldPassword;
					
}
	
	
	@PostMapping("/adminLogin")
	public Map<String,String> adminLogin(@RequestBody AdminProfile admin) throws HandlingException{
		
		String email = admin.getEmail().trim();
		String password = admin.getPassword().trim();
		
		String regex_email = "^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
		String regex_password = "^[a-zA-Z0-9@$!%*#?&]{8,}$";
		
		if(email.isEmpty() || password.isEmpty() ) {
			System.out.println("Fields can not be empty");
		
		}else {
			if(email.matches(regex_email) && password.matches(regex_password)) {
				AdminProfile details = adminProfileRepo.findByEmailAndPassword(email,password);
				
				Map<String, String> map=new HashMap<String, String>();
				if(details != null) {			
					map.put("Status", "ACCETED");
					map.put("Message", "You are Logged In");
				}else {
					map.put("Status", "Forbidden");
					map.put("Message", "Invalid Credentials");
				}
				
				return map;
			}else {
				System.out.println("Invalid data");
				throw new HandlingException("Invalid data");
			}
		}
		
		return null;	
		
		
	}
	
	@PostMapping("/registerEmployee")
	@ResponseBody
	public Map<String,String> register( @RequestBody EmployeeDetails reg, @RequestParam(value ="confirmPassword") String confirmPassword) throws HandlingException{
		String pattern="^.(?=.{8,})(?=..[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).$";
		String pass = "";
		String pattern1="(^$|[0-9]{10})";
		String pattern2="^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
		

		if(!reg.getContact().toString().matches(pattern1))
		{
		
			throw new HandlingException("Invalid contact number");

		}

		if(!reg.getEmail() .matches(pattern2))
		{
			System.out.println("password is too weak");
			throw new HandlingException("Please enter valid email");

		}


		if(!reg.getPassword().equals(confirmPassword))
		{
			System.out.println("Password and confirm_password are not same");
			throw new HandlingException("password and confirm_password are not same");
		

     	}
		
		EmployeeDetails emp = employeeDetailsRepo.findByContact(reg.getContact());
		if(emp == null) {
			reg.setEmployeeId(EmployeeIdCounter());
			EmployeeDetails register = employeeDetailsRepo.save(reg);
			if(register != null) {
				 Map<String,String> map=new HashMap<String, String>();
				 
				 reponseDetails d1= new reponseDetails(200,"There is no entries Yet.",new Date());
				 map.put("Status Code", "ACCEPTED");
				 map.put("Message", "Inserted successfully");
				 map.put("Employee Id", reg.getEmployeeId());
				 return map;
			}else {
				System.out.println("error");
			}
		}else {
			System.out.println("Employee Is already Registered");
		}
		
		return null;
	}
	
	
	

		
		
	@PostMapping("/employeeDetails")
	 public Map<String,Object>  employeeDetails() throws IOException {
		 
		 List<EmployeeDetails> details = employeeDetailsRepo.findAll();
		 Map<String,Object> map=new HashMap<String, Object>();
		 
		 if(details.isEmpty()) {
			 reponseDetails d1= new reponseDetails(200,"There is no entries Yet.",new Date());
			 map.put("Status Code", d1.getStatusCode());
			 map.put("Date and Time", d1.getTime());
			 map.put("Message", d1.getMessage());			 
		 }else { 
			 reponseDetails d1= new reponseDetails(200,"Success.",new Date(),details);
			 map.put("Status Code", d1.getStatusCode());
			 map.put("Date and Time", d1.getTime());
			 map.put("Message", d1.getMessage());
			 map.put("Employee Details",details);  
		 }	

		 return map; 
	 }
	
	
	
	
	
	
	private int tokenCounter() {
        int token =0;

        //TokenGenerator
        token = counterRepo.findByName("TokenGenerator").getCount();
        int count = counterRepo.incrementToken();
        if(count == 1) {
            return token;
        }
        return 0;
    }

	
	@PostMapping("/contactUsEntries")
    public List<ContactUs> contactUsEntries() throws HandlingException {
        List<ContactUs> details = contactUsRepository.findAll();
        if(details.isEmpty()) {
            System.out.println("No Entries Yet!");
            throw new HandlingException("No Entries Yet!");

        }else {
            return details;
        }

      

    }
	
	private String EmployeeIdCounter() {
		String employeeId = "";
		
		//TokenGenerator
		if( counterRepo.findByName("EmployeeId") !=null)
		{
		int count  = counterRepo.findByName("EmployeeId").getCount();
		int res = counterRepo.incrementEmployeeId();
		if(res == 1) {
			employeeId = "EMP"+count;
			return employeeId;
		}
	}
		
		return null;
	}
}
