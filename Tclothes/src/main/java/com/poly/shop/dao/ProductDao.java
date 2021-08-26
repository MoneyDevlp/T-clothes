package com.poly.shop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.shop.bean.Product;
import com.poly.shop.bean.Report;

public interface ProductDao extends JpaRepository<Product, Long>{
	Page<Product> findByNameContaining(String name, Pageable pageable);
	@Query(value = "SELECT p FROM Product p WHERE p.unitPrice BETWEEN ?1 AND ?2")
	Page<Product> findByUnitPriceBetween(double minPrice, double maxPrice,Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.categoryId=?1")
	Page<Product> findByCategoryId(Long cid, Pageable pageable);
	
	@Query(value = "SELECT p FROM Product p ORDER BY p.unitPrice DESC")
	Page<Product> findByProduct(Pageable pageable);
	
	@Query(value = "SELECT p FROM Product p ORDER BY p.unitPrice ASC")
	Page<Product> findByProductAsc(Pageable pageable);
	
	@Query(value = "SELECT new Report(o.category,sum(o.unitPrice),count(o))" + " FROM Product o" + " GROUP BY o.category"
            + " ORDER BY sum(o.unitPrice) DESC")
	List<Report> getInventoryByCategory();
}
