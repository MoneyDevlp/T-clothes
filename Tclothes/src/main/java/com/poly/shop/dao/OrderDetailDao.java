package com.poly.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.shop.bean.Order;
import com.poly.shop.bean.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long>{
	List<OrderDetail> findByOrder(Order order);
}
