package com.poly.shop.service;

import java.util.List;

import com.poly.shop.bean.Order;
import com.poly.shop.bean.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> findByOrder(Order order);

}
