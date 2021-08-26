package com.poly.shop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long orderId;
	@Temporal(TemporalType.DATE)
	Date orderDate;
	@NotBlank(message = "Không để trống tên Adress!")
	String adress;
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	List<OrderDetail> orderDetails;
}

