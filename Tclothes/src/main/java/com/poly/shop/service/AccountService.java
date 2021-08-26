package com.poly.shop.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.poly.shop.bean.Account;

public interface AccountService {

	Account getById(String id);

	void deleteById(String id);

	List<Account> findAll();

	<S extends Account> S save(S entity);

	Account findById(String username);

	void updatePassword(Account account, String newPassword);

	Account getByResetPasswordToken(String token);

	void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;


	

	
	
	
}
