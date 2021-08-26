package com.poly.shop.config;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.shop.bean.Account;
import com.poly.shop.service.AccountService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Bean
	public BCryptPasswordEncoder getPassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account account = accountService.findById(username);
				String password = pe.encode(account.getPassword());
				String[] roles = account.getAuthorities().stream().map(au -> au.getRole().getId())
				.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "Not found!");
			}
		});

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable().cors().disable();

		
		http.authorizeRequests()
//		.antMatchers("/user").hasAnyRole("USER","ADMIN","STAFF")
//		.antMatchers("/admin").hasAnyRole("ADMIN","STAFF")
		.antMatchers("/checkout").authenticated()
		.anyRequest().permitAll();
		
		
		http.formLogin()
		.loginPage("/auth/login/form")
		.loginProcessingUrl("/auth/login")
		.defaultSuccessUrl("/user",false)
		.failureUrl("/auth/login/error")
		.usernameParameter("username")
		.passwordParameter("password");
		
		
		http.logout()
		.logoutUrl("/auth/logoff")
		.logoutSuccessUrl("/auth/logoff/success");
		
	}
}
