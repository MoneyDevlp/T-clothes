package com.poly.shop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.shop.bean.Order;
import com.poly.shop.bean.OrderDetail;
import com.poly.shop.bean.Report;
import com.poly.shop.dao.OrderDao;
import com.poly.shop.dao.OrderDetailDao;
import com.poly.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderDetailDao orderDetailDao;

	@Override
	public <S extends Order> S save(S entity) {
		return orderDao.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}


	@Override
	public Order create(JsonNode order) {
		ObjectMapper mapper = new ObjectMapper();
		Order or = mapper.convertValue(order, Order.class);
		orderDao.save(or);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> list = mapper.convertValue(order.get("orderDetails"), type)
		.stream().peek(d -> d.setOrder(or)).collect(Collectors.toList());
		orderDetailDao.saveAll(list);
		
		return or;
		
	}

	@Override
	public Order findById(Long orderId) {
		return orderDao.findById(orderId).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return orderDao.findByUsername(username);
	}

	@Override
	public List<Object[]> getAmountByOrder() {
		return orderDao.getAmountByOrder();
	}

	@Override
	public List<Object[]> getAmountBydate(Integer day,Integer month, Integer year) {
		return orderDao.getAmountBydate(day,month,year);
	}

	@Override
	public List<Object[]> getAmountBymonth(Integer month, Integer year) {
		return orderDao.getAmountBymonth(month, year);
	}

	
	
	
	
}
