package com.revature.hms.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.HotelManagementReceptionistApplication;
import com.revature.hms.booking.model.Booking;
import com.revature.hms.booking.service.BookingService;
import com.revature.hms.booking.service.BookingServiceImpl;


@RestController
@RequestMapping("bookRoom")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
	
	Logger LOGGER=LoggerFactory.getLogger(BookingServiceImpl.class);
	
	boolean result=false;
	@Autowired
	HotelManagementReceptionistApplication mailApplication;
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping
	public ResponseEntity<List<Booking>> getBookings()
	{
		ResponseEntity<List<Booking>> responseEntity=null;
		List<Booking> bookings = bookingService.viewBookingRecords();

		LOGGER.info("******************** LIST OF BOOKING RECORDS");
		
		if(bookings.size()==0)
		{
			LOGGER.info("******************** SIZE OF BOOKING RECORDS = 0");
					
			responseEntity = new ResponseEntity<List<Booking>>(bookings,HttpStatus.NO_CONTENT);
			
		}else
		{
			LOGGER.info("********************  BOOKING RECORD IS PRESENT ");
			
			responseEntity = new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
				
		}
		return responseEntity;
	}
	
	@GetMapping("/bookedRoomsGreaterThan/{roomNumber}")    //room number> 0 
	public List<Booking> allocatedRooms(@PathVariable("roomNumber")int roomNumber)
	{	LOGGER.info("******************** LIST OF CUSTOMER DETAILS GREATER THAN 0 ROOM NUMBERS");
	
		return bookingService.viewBookedRooms(roomNumber);
		
	}
	
	@GetMapping("/cancelledRooms/{cancellation}")	//cancellations = yes
	public List<Booking>cancelledRooms(@PathVariable("cancellation")String cancellation)
	{
		LOGGER.info("******************** LIST OF CANCELLED ROOMS DETAILS FROM BOOKING RECORDS ");
		return bookingService.viewCancellations(cancellation);
	}
	
	
	@PutMapping()
	public ResponseEntity<String> updateBookingRecord(@RequestBody Booking booking)
	{
		ResponseEntity<String> responseEntity = null;
		int bookingRoom = booking.getRoomNumber();
		String message=null;
		LOGGER.info("******************** ROOM NUMBER IS ALLOCATED TO CUSTOMER ");
		
		if(bookingService.isRoomNumberExists(bookingRoom))//200
		{
			LOGGER.info("******************** ROOM IS FILLED");
			
		message = "Room : "+bookingRoom+" 	already allocated, Please allocate other room";
		responseEntity = new ResponseEntity<String>(message,HttpStatus.OK);
		}
		else
		{
			LOGGER.info("******************** ROOM IS EMPTY, ALLOCATE ROOM ");
			
			result = bookingService.updateRecord(booking);
			message = "Room : "+bookingRoom+" allocated";
			/*
			 * if(result) { int roomNumber = booking.getRoomNumber(); String from =
			 * "Taj-Restaurant"; String toUserMail = booking.getEmail(); String
			 * subject="Room Booking Status"; String message1=null;
			 * 
			 * message1="Congrajulations! and Hearty Welcome "+
			 * "\n Dear "+booking.getUserName()+"\n"
			 * +"You have successfully Booked a room in our Hotel with Room no : "
			 * +booking.getRoomNumber()+
			 * "With an initial Amount of INR: "+booking.getAmountPayed();
			 * mailApplication.sendMail(from, toUserMail,subject , message1);
			 * LOGGER.info("Mail Sent Successfully..."); }
			 */
			responseEntity = new ResponseEntity<String>(message,HttpStatus.OK);
	}
		return responseEntity;
	}
	
	@PostMapping()
	public ResponseEntity<String> mailAcknowledgement(@RequestBody Booking booking)
	{
		ResponseEntity<String> responseEntity = null;
		 int roomNumber = booking.getRoomNumber();
		 String from = "Taj-Restaurant";
		 String toUserMail = booking.getEmail();
		 String subject="Room Booking Status";
		 String message=null;
	 if(result)
	 {
		 message="Congratulations! and Hearty Welcome "+ 
				 "\n Dear "+booking.getUserName()+"\n"
				 +"You have successfully Booked a room in our Hotel with Room no : "
				 +booking.getRoomNumber()+
				 "With an initial Amount of INR: "+booking.getAmountPayed();
		 mailApplication.sendMail(from, toUserMail,subject , message);
	 }
	  LOGGER.info("Mail Sent Successfully...");
		return responseEntity;
		
	}
	
	@DeleteMapping("{userName}")
	public ResponseEntity<String> deleteCancelledRecord(@PathVariable("userName")String userName)
	{
		LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");
		ResponseEntity<String> responseEntity = null;
						//need to update the record into booking history then delete record in db. 
		bookingService.deleteRecord(userName);
		String message ="User : "+userName+ " Record deleted ";
		responseEntity = new ResponseEntity<String>(message,HttpStatus.OK);
		return responseEntity;
	}
	
	
}
