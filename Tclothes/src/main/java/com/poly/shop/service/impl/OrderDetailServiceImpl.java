package com.poly.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.shop.bean.Order;
import com.poly.shop.bean.OrderDetail;
import com.poly.shop.dao.OrderDetailDao;
import com.poly.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	OrderDetailDao orderDetailDao;

	@Override
	public List<OrderDetail> findByOrder(Order order) {
		return orderDetailDao.findByOrder(order);
	}
	
	
}
