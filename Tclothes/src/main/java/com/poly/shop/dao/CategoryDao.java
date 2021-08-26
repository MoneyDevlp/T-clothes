package com.poly.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.shop.bean.Category;

public interface CategoryDao extends JpaRepository<Category, Long>{

}
