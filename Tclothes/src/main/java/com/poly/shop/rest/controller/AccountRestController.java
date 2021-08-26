package com.poly.shop.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.shop.bean.Account;
import com.poly.shop.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public List<Account> getAll(){
		return accountService.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<Account> post(@Valid @RequestBody Account account) {
		Account ac = accountService.save(account);
		return new ResponseEntity<Account>(ac, HttpStatus.CREATED);
	}
	
	@PutMapping("{username}")
	public Account put(@PathVariable("username") String username,@RequestBody Account account) {
		return accountService.save(account);
	}
	
	@DeleteMapping("{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.deleteById(username);
	}
}
