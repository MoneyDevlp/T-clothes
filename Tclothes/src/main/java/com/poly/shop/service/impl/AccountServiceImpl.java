package com.poly.shop.service.impl;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.shop.bean.Account;
import com.poly.shop.dao.AccountDao;
import com.poly.shop.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao accountDao;
	
	
	@Override
	public <S extends Account> S save(S entity) {
		return accountDao.save(entity);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public void deleteById(String id) {
		accountDao.deleteById(id);
	}

	@Override
	public Account getById(String id) {
		return accountDao.getById(id);
	}

	@Override
	public Account findById(String username) {
		return accountDao.findById(username).get();
	}

	@Override
	public void updateResetPasswordToken(String token, String email) throws AccountNotFoundException {
        Account account = accountDao.findByEmail(email);
        if (account != null) {
        	account.setResetPasswordToken(token);
            accountDao.save(account);
        } else {
            throw new AccountNotFoundException("Could not find any account with the email " + email);
        }
    }
	
	@Override
	public Account getByResetPasswordToken(String token) {
        return accountDao.findByResetPasswordToken(token);
    }
	
	@Override
	public void updatePassword(Account account, String newPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(newPassword);
        account.setPassword(newPassword);
         
        account.setResetPasswordToken(null);
        accountDao.save(account);
    }
	
}
