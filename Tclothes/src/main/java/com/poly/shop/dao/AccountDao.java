package com.poly.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.shop.bean.Account;

public interface AccountDao extends JpaRepository<Account, String>{
	
	@Query(value = "SELECT a FROM Account a WHERE a.email = ?1")
	public Account findByEmail(String email);
	
	public Account findByResetPasswordToken(String token);
}
