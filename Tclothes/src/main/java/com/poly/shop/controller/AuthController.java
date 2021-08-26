package com.poly.shop.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.shop.bean.Account;
import com.poly.shop.config.Utility;
import com.poly.shop.service.AccountService;

import net.bytebuddy.utility.RandomString;

@Controller
public class AuthController {
	
	@Autowired
	AccountService acountService;
	@Autowired
	HttpSession session;
	@Autowired
	Utility utility;
	
	@GetMapping("/auth/login/form")
	public String form() {
		return "auth/login";
	}
	
	
	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/auth/login/form";
	}
	
	@RequestMapping("/auth/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "forward:/auth/login/form";
	}
	
	@GetMapping("/auth/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "auth/register";
	}
	
	@PostMapping("/auth/register/save")
	public String save(Model model, Account account) {
		acountService.save(account);
		model.addAttribute("message", "Account is registed!");
		return "auth/register";
	}
	
	@GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
		return "auth/fogot";
    }
	
	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
	    String email = request.getParameter("email");
	    String token = RandomString.make(30);
	     
	    try {
	        acountService.updateResetPasswordToken(token, email);
	        String resetPasswordLink = utility.getSiteURL(request) + "/reset_password?token=" + token;
	        utility.sendEmail(email, resetPasswordLink);
	        model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
	         
	    } catch (AccountNotFoundException ex) {
	        model.addAttribute("error", ex.getMessage());
	    } catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error while sending email");
	    }
	         
	    return "auth/fogot";
	}
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    Account account = acountService.getByResetPasswordToken(token);
	    model.addAttribute("token", token);
	     
	    if (account == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    }
	     
	    return "auth/resetPass";
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
	    String token = request.getParameter("token");
	    String password = request.getParameter("password");
	     
	    Account account = acountService.getByResetPasswordToken(token);
	    model.addAttribute("title", "Reset your password");
	     
	    if (account == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    } else {           
	    	acountService.updatePassword(account, password);
	         
	        model.addAttribute("message", "You have successfully changed your password.");
	    }
	     
	    return "auth/resetPass";
	}
}
