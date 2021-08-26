package com.poly.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.shop.bean.Order;
import com.poly.shop.bean.Report;

public interface OrderService {


	List<Order> findAll();

	<S extends Order> S save(S entity);

	Order create(JsonNode order);

	Order findById(Long orderId);

	List<Order> findByUsername(String username);

	List<Object[]> getAmountByOrder();

	List<Object[]> getAmountBydate(Integer day,Integer month, Integer year);

	List<Object[]> getAmountBymonth(Integer month, Integer year);

	

}
