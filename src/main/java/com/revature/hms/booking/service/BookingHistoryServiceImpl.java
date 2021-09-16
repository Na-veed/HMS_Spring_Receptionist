package com.revature.hms.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.booking.model.Booking;
import com.revature.hms.booking.model.BookingHistory;
import com.revature.hms.booking.repository.BookingHistoryRepository;


@Service

public class BookingHistoryServiceImpl implements BookingHistoryService{
	@Autowired
	private BookingHistoryRepository bookingHistoryRepository;
	
	@Autowired
	private BookingService bookingService;

	@Override
	public boolean addToHistory(String userName) {
		Booking customerBooking = new Booking();
		customerBooking =  bookingService.findByUserName(userName);
		BookingHistory bookingHistory=new BookingHistory(customerBooking.getUserName(),customerBooking.getCustomerName(), customerBooking.getCustomerMobileno(), customerBooking.getEmail(),customerBooking.getRoomType(),customerBooking.getNumberOfRooms(),customerBooking.getNumberOfMembers(), customerBooking.getCustomerCheckIn(), customerBooking.getCustomerCheckOut(),customerBooking.getRoomNumber(),customerBooking.getCancellation(), customerBooking.getBookingStatus(), customerBooking.getAmountPaid());
		bookingHistoryRepository.save(bookingHistory);
		return true;
	}

}
