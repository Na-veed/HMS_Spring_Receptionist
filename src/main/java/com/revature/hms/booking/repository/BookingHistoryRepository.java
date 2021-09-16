package com.revature.hms.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hms.booking.model.BookingHistory;

public interface BookingHistoryRepository extends JpaRepository<BookingHistory, String>{

}
