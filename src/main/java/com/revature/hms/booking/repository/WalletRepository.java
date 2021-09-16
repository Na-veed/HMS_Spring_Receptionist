package com.revature.hms.booking.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.revature.hms.booking.model.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, String> {

	@Modifying
	@Query(value = "UPDATE FROM Wallet set money = money + ?2 where username = ?1")
	public void addMoneyforCancellation(String username, int price);
	
	@Modifying
	@Query(value = "UPDATE FROM Wallet set money = money - ?2 where username = ?1")
	public void deductMoneyFromWallet(String username, int price);
}
