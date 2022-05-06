package com.capg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name ="user_details")
public class UserDetail {
	
	  

	    private String firstName;
	    private String lastName;
	    private String middleName;

	    @Id
	    private String contact;
	    private String email;
	    private String password;
	    
	    private String aadharCardNumber ; 


	    public UserDetail( String firstName, String lastName, String middleName, String contact,
				String email, String password, String aadharCardNumber, Address address) {
			super();
		
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
			this.contact = contact;
			this.email = email;
			this.password = password;
			this.aadharCardNumber = aadharCardNumber;
			this.address = address;
		}


		public String getAadharCardNumber() {
			return aadharCardNumber;
		}


		public void setAadharCardNumber(String aadharCardNumber) {
			this.aadharCardNumber = aadharCardNumber;
		}


		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
	    @JoinColumn(name = "contact")
	    private Address address;

	    

		public UserDetail() {
			super();
		}



		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getMiddleName() {
			return middleName;
		}


		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}


		public String getContact() {
			return contact;
		}


		public void setContact(String contact) {
			this.contact = contact;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Address getAddress() {
			return address;
		}


		public void setAddress(Address address) {
			this.address = address;
		}
	    
	    
	
	
}
