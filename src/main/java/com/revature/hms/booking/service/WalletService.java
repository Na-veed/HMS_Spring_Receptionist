package com.revature.hms.booking.service;

import com.revature.hms.booking.model.Wallet;

public interface WalletService {

	public boolean addWallet(Wallet wallet);
	
	public boolean deleteWallet(String username);
	
	public boolean addMoneyForCancellation(String username, int amount);
	
	public void sendMail(String from,String toReceiver,String subject,String message);

	boolean deductMoney(String username, int money);
	
}
