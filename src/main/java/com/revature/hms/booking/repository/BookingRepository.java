package com.revature.hms.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.revature.hms.booking.model.Booking;



public interface BookingRepository extends CrudRepository<Booking,String>{
	
	public List<Booking>findByCancellation(String cancellation);
	public List<Booking>findByRoomNumberGreaterThan(int roomNumber);
	public Optional<Booking> findByRoomNumber(int roomNumber);
	//public List<Booking> findByBookingEmail(String email);
}
