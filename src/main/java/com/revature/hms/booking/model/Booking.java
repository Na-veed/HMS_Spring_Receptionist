package com.revature.hms.booking.model;


import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name ="bookings")	
@Data
public class Booking {
	
	@Id
	private String userName;
	
	@Column(nullable= false)
	private String customerName;
	
	@Column(nullable=false, unique=true)
	private String customerMobileno;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String roomType;
	
	@Column(nullable=false)
	private int numberOfMembers;
	
	@Column(nullable=false)
	private int numberOfRooms;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Calendar customerCheckIn;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Calendar customerCheckOut;
	
	@Column
	private int roomNumber;
	
	@Column
	private String cancellation;
	
	@Column(nullable=false)
	private String bookingStatus;
	
	@Column(nullable=false)
	private int amountPaid;			
	
}

