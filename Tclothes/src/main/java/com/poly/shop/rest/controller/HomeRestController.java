package com.poly.shop.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.shop.bean.Report;
import com.poly.shop.service.OrderService;
import com.poly.shop.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reports")
public class HomeRestController {
	
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	
	
	@GetMapping()
	public List<Report> getAll(){
		return productService.getInventoryByCategory();
	}
	
	@GetMapping("amount")
	public List<Object[]> getAllAmount(){
		return orderService.getAmountByOrder();
	}
	
	@GetMapping("order/{day}/{month}/{year}")
	public List<Object[]> getAllAmountByYear(@PathVariable("day") Integer day,@PathVariable("month") Integer month, @PathVariable("year") Integer year){
		return orderService.getAmountBydate(day,month,year);
	}
	
	@GetMapping("ordermonth/{month}/{year}")
	public List<Object[]> getAllAmountByMonth(@PathVariable("month") Integer month, @PathVariable("year") Integer year){
		return orderService.getAmountBymonth(month,year);
	}
}
