package com.revature.hms.booking.service;

import java.util.List;

import com.revature.hms.booking.model.Booking;


public interface BookingService {
	
	public List<Booking> viewBookingRecords();
	public List<Booking> viewCancellations(String cancellation);
	public List<Booking> viewBookedRooms(int roomNumber);
	public boolean isRoomNumberExists(int roomNumber);
	public boolean deleteRecord(String userName);
	public boolean updateRecord(Booking booking);
	public boolean deleteByUserName(String userName);
	public Booking findByUserName(String userName);
	}
