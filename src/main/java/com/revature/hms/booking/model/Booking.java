package com.revature.hms.booking.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "booking")	
@Data
public class Booking {
	
	
	
	@Id
	private String userName;
	private String email;
	private int roomNumber=0;				
	private String cancellation;		
	private int amountPayed;				
	
}

