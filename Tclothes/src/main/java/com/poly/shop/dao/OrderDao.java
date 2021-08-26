package com.poly.shop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.shop.bean.Order;


public interface OrderDao extends JpaRepository<Order, Long>{
	
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);
	
	@Query(value = "SELECT o.orderDate,SUM(d.quantity),SUM(d.quantity*d.unitPrice) from Order o "
			+ "INNER JOIN OrderDetail d ON o.orderId = d.order.orderId GROUP BY o.orderDate")
	List<Object[]> getAmountByOrder();
	
	@Query(value = "SELECT o.orderDate, SUM(d.quantity),SUM(d.quantity*d.unitPrice) from Order o "
			+ "INNER JOIN OrderDetail d ON o.orderId = d.order.orderId WHERE DAY(o.orderDate)=?1 AND MONTH(o.orderDate)=?2 AND YEAR(o.orderDate)=?3 GROUP BY o.orderDate")
	List<Object[]> getAmountBydate(Integer day,Integer month, Integer year);
	
	@Query(value = "SELECT MONTH(o.orderDate), SUM(d.quantity),SUM(d.quantity*d.unitPrice) from Order o "
			+ "INNER JOIN OrderDetail d ON o.orderId = d.order.orderId WHERE MONTH(o.orderDate)=?1 AND YEAR(o.orderDate)=?2 GROUP BY MONTH(o.orderDate)")
	List<Object[]> getAmountBymonth(Integer month, Integer year);
}
