package com.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.shop.bean.Product;
import com.poly.shop.bean.Report;
import com.poly.shop.dao.ProductDao;
import com.poly.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;

	@Override
	public <S extends Product> S save(S entity) {
		return productDao.save(entity);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productDao.findAll(pageable);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

	@Override
	public Page<Product> findByNameContaining(String name, Pageable pageable) {
		return productDao.findByNameContaining(name, pageable);
	}

	@Override
	public Page<Product> findByUnitPriceBetween(double minPrice, double maxPrice, Pageable pageable) {
		return productDao.findByUnitPriceBetween(minPrice, maxPrice, pageable);
	}

	@Override
	public Page<Product> findByCategoryId(Long cid, Pageable pageable) {
		return productDao.findByCategoryId(cid, pageable);
	}

	@Override
	public List<Report> getInventoryByCategory() {
		return productDao.getInventoryByCategory();
	}
	
	@Override
	public Page<Product> findByProduct(Pageable pageable) {
		return productDao.findByProduct(pageable);
	}

	@Override
	public Page<Product> findByProductAsc(Pageable pageable) {
		return productDao.findByProductAsc(pageable);
	}

	
}
