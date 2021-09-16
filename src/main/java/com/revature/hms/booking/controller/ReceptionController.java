package com.revature.hms.booking.controller;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.booking.model.Booking;
import com.revature.hms.booking.model.Wallet;
import com.revature.hms.booking.service.BookingService;
import com.revature.hms.booking.service.BookingServiceImpl;
import com.revature.hms.booking.service.WalletService;

@RestController
@RequestMapping("/Reception")
public class ReceptionController {

	Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	WalletService walletService;

	@Autowired
	BookingService bookingService;

	@PostMapping("/save")
	public ResponseEntity<String> addWallet(@RequestBody Wallet wallet) {

		ResponseEntity<String> responseEntity = null;
		walletService.addWallet(wallet);
		responseEntity = new ResponseEntity<String>("wallet added successfully", HttpStatus.OK);
		return responseEntity;

	}

	@SuppressWarnings("deprecation")
	@PutMapping("/{price}/{username}/{email}/{dateIn}")
	ResponseEntity<Boolean> addMoneyForCancellation(@PathVariable("price") int price,
			@PathVariable("username") String username,
			@PathVariable("dateIn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateIn, @PathVariable String email,
			Booking bookingTable) throws ParseException {
		ResponseEntity<Boolean> responseEntity = null;

		String message = null;
		String from = "naveedimran2802@gmail.com";
		String to = email;
		String subject = "Regarding the cancellation request";
		Date currentDate = new Date();

		int monthDifference = dateIn.getMonth() - currentDate.getMonth();
		System.out.println(monthDifference);

		int dateDifference = dateIn.getDate() - currentDate.getDate();

		System.out.println(dateDifference);
		if (monthDifference != 0) {
			dateDifference += 31;
			System.out.println(dateDifference);
			if (dateDifference > 31) {
				int amount = (price / 100) * 10;
				Boolean result = walletService.addMoneyForCancellation(username, amount);
				LOGGER.info("Booking Cancelled and money added to wallet successfully");
				if (result) {
					LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");

					// need to update the record into booking history then delete record in db.

					bookingService.deleteRecord(username);

					message = "Dear " + username + "," + "\n"
							+ "\n Your cancellation request has been succesfully processed and your booking "
							+ "has been cancelled successfully"
							+ "Your payment during the booking of you hotel room has been refunded successfully to your "
							+ "respective wallet." + "\n \nThank you for thinking about us for your hotel needs"
							+ "\n \nPlease query us at " + "menando@gmail.com, we would love to hear from you" + "\n \n"
							+ "Regards" + "\n Menando resort";
					walletService.sendMail(from, to, subject, message);
					responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
				} else {
					LOGGER.error("Problem occured during cancellation");
				}
			}
		}

		if (monthDifference == 0 && (dateDifference >= 0 && dateDifference <= 3)) {
			System.out.println("no refund is called");
			int amount = 0;
			Boolean result = walletService.addMoneyForCancellation(username, amount);
			if (result) {
				LOGGER.info("Booking Cancelled successfully");
				LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");

				// need to update the record into booking history then delete record in db.

				bookingService.deleteRecord(username);
				message = "Dear " + username + "," + "\n"
						+ "\n Your cancellation request has been succesfully processed and your booking "
						+ "has been cancelled successfully"
						+ "Your payment during the booking of you hotel room has not been refunded due to the "
						+ "hotel policies."

						+ "\n \nPlease query us at " + "menando@gmail.com, we would love to hear from you" + "\n \n"
						+ "Regards" + "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);

			} else {
				LOGGER.error("Problem occured during cancellation");
			}
		}

		else if ((monthDifference == 0 || monthDifference == 1) && (dateDifference >= 4 && dateDifference <= 30)) {
			int amount = (price / 100) * 5;
			System.out.println("amount for  greater than 4 days and lesser than 30 is :" + amount);
			Boolean result = walletService.addMoneyForCancellation(username, amount);
			if (result) {
				LOGGER.info("Booking Cancelled and money added to wallet successfully");
				LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");

				// need to update the record into booking history then delete record in db.

				bookingService.deleteRecord(username);
				message = "Dear " + username + "," + "\n"
						+ "\n Your cancellation request has been succesfully processed and your booking "
						+ "has been cancelled successfully"
						+ "Your payment during the booking of you hotel room has been refunded successfully to your "
						+ "respective wallet."
						+ "\n Due to policies and conditions only 5% of your payed amount has been refunded to your wallet"
						+ "\n \nThank you for thinking about us for your hotel needs" + "\n \nPlease query us at "
						+ "menando@gmail.com, we would love to hear from you" + "\n \n" + "Regards"
						+ "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
			} else {
				LOGGER.error("Problem occured during cancellation");
			}
		}

		return responseEntity;

	}

	@PutMapping("/{status}/{username}/{price}")
	ResponseEntity<Boolean> deductMoneyFromCheckOut(@PathVariable("price") int price,
			@PathVariable("username") String username, @PathVariable("status") String status) {

		System.out.println(status);
		ResponseEntity<Boolean> responseEntity = null;
		if (status.compareToIgnoreCase("booked") == 0) {
			System.out.println(price);
			price = (price / 100) * 10;
			walletService.deductMoney(username, price);
			responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else if (status.compareToIgnoreCase("IN") == 0) {
			price = (price / 100) * 40;
			walletService.deductMoney(username, price);
			responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else if (status.compareToIgnoreCase("OUT") == 0) {
			price = (price / 100) * 50;
			walletService.deductMoney(username, price);
			responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

}
