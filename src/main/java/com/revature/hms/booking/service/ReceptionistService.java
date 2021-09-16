package com.revature.hms.booking.service;



import com.revature.hms.booking.model.Receptionist;



public interface ReceptionistService {

	
	public boolean receptionistLogin(int receptionistId, String receptionistPassword);
	
	public boolean isReceptionistExists(int receptionistId);
	
	public boolean updateReceptionist(Receptionist receptionist);
	
	public Receptionist viewDetails(int receptionistId);
	

}
