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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long productId;
	@NotBlank(message = "Không để trống Name!")
	String name;
	@NotNull(message = "Không để trống Quantity!")
	@PositiveOrZero(message = "Quantity không được nhỏ hơn 0!")
	Integer quantity;
	@NotNull(message = "Không để trống UnitPrice!")
	@PositiveOrZero(message = "UnitPrice không được nhỏ hơn 0!")
	Double unitPrice;
	@NotBlank(message = "Không để trống Image!")
	String image;
	@NotBlank(message = "Không để trống Description!")
	String description;
	@NotNull(message = "Không để trống Discount!")
	@PositiveOrZero(message = "Discount không được nhỏ hơn 0!")
	Double discount;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Không để trống EnteredDate!")
	Date enteredDate;
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	@NotNull(message = "Không để trống Category!")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	List<OrderDetail> orderDetails;
}

