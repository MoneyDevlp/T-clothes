package com.poly.shop.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.shop.bean.Order;
import com.poly.shop.bean.OrderDetail;
import com.poly.shop.dao.OrderDao;
import com.poly.shop.dao.OrderDetailDao;
import com.poly.shop.service.OrderDetailService;
import com.poly.shop.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	OrderDetailDao dDao;
	@Autowired OrderDao oDao;
	
	@PostMapping()
	public ResponseEntity<Order> post(@Valid @RequestBody JsonNode order) {
		Order or = orderService.create(order);
		return new ResponseEntity<Order>(or,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Order> getAll(){
		return orderService.findAll();
	}
	
	@GetMapping("{orderId}")
	public List<OrderDetail> getById(@PathVariable("orderId") Long orderId) {
		Order order = oDao.findById(orderId).get();
		return dDao.findByOrder(order);
	}
	
}
