package com.poly.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	@Id
	@NotBlank(message = "Không để trống Username!")
	String username;
	@NotBlank(message = "Không để trống Fullname!")
	String fullname;
	@NotBlank(message = "Không để trống Password!")
	String password;
	@NotBlank(message = "Không để trống Email!")
	@Email(message = "Không đúng định dạng Email!")
	String email;
	@NotBlank(message = "Không để trống Phone!")
	@PositiveOrZero(message = "Phone không được nhỏ hơn 0!")
	@Size(min = 10,max = 10,message = "Không đúng định dạng Phone!")
	String phone;
	@NotNull(message = "Vui lòng chọn Gender!")
	Boolean gender;
	@Column(name = "reset_password_token")
    String resetPasswordToken;
	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	
}
