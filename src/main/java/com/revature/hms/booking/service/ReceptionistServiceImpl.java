package com.revature.hms.booking.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.booking.model.Receptionist;
import com.revature.hms.booking.repository.ReceptionistRepository;


@Service
public class ReceptionistServiceImpl implements ReceptionistService{

	
	@Autowired
	ReceptionistRepository receptionistRepository;

	

	@Override
	public boolean isReceptionistExists(int receptionistId) {
		Optional<Receptionist> receptionistData = receptionistRepository.findById(receptionistId);
		return receptionistData.isPresent();
	}


	@Override
	public boolean updateReceptionist(Receptionist receptionist) {
		receptionistRepository.save(receptionist);
		return true;
	}

	@Override
	public Receptionist viewDetails(int receptionistId) {
		Optional<Receptionist> receptionistData = receptionistRepository.findById(receptionistId);
		Receptionist receptionist = receptionistData.get();
		return receptionist;
	}


	@Override
	public boolean receptionistLogin(int receptionistId, String receptionistPassword) {
		Optional<Receptionist> receptionistData = receptionistRepository.findByReceptionistIdAndReceptionistPassword(receptionistId, receptionistPassword);
		if(receptionistData!=null) {
			return true;
		}
		else {
			return false;
		}
		 
	}

}
