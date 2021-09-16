package com.revature.hms.booking.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.revature.hms.booking.model.Receptionist;



public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer>
{
		
	public Optional<Receptionist> findByReceptionistIdAndReceptionistPassword(int receptionistId, String receptionistPassword);
	 
}
