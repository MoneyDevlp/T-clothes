package com.poly.shop.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.shop.bean.Authority;
import com.poly.shop.dao.AccountDao;
import com.poly.shop.dao.AuthorityDao;
import com.poly.shop.dao.RoleDao;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {
	
	@Autowired
	AccountDao accountDao;
	@Autowired
	AuthorityDao authorityDao;
	@Autowired
	RoleDao roleDao;
	
	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authorities", authorityDao.findAll());
		map.put("roles", roleDao.findAll());
		map.put("accounts", accountDao.findAll());
		return map;
	}
	
	@PostMapping("/rest/authorities")
	public Authority post(@RequestBody Authority authority) {
		return authorityDao.save(authority);
	}
	
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityDao.deleteById(id);
	}
}
