package com.poly.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
	
	@RequestMapping("blog")
	public String blog() {
		return "contact/blog";
	}
	
	@RequestMapping("contact")
	public String contact() {
		return "contact/contact";
	}
}
