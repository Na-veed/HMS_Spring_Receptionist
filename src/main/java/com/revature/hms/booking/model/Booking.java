package com.revature.hms.booking.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")	

public class Booking {
	
	
	
	@Id
	private String userName;
	private String email;
	private int roomNumber=0;				//default room number before allocation
	private String cancellation;		//yes - true / no-false
	private int amountPayed;				//booking amount

	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getCancellation() {
		return cancellation;
	}

	public void setCancellation(String cancellation) {
		this.cancellation = cancellation;
	}

	public int getAmountPayed() {
		return amountPayed;
	}

	public void setAmountPayed(int amountPayed) {
		this.amountPayed = amountPayed;
	}
	
	
}

