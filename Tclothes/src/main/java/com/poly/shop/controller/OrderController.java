package com.poly.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.shop.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("checkout")
	public String checkOut() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/detail/{orderId}")
	public String detail(Model model, @PathVariable("orderId") Long orderId) {
		model.addAttribute("order", orderService.findById(orderId));
		return "order/orderDetail";
	}
	
	@RequestMapping("/order/list")
	public String orderlist(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/listOrder";
	}
}
