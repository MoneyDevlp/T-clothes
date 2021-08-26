package com.poly.shop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.shop.bean.Category;
import com.poly.shop.bean.Product;
import com.poly.shop.service.CategoryService;
import com.poly.shop.service.ProductService;

@Controller
public class ShopController {
	
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
//	@RequestMapping("/shop")
//	public String shop(Model model,@RequestParam("p") Optional<Integer> p,@RequestParam("cid") Optional<Long> cid) {
//		if(cid.isPresent()) {
//			List<Product> pro = productService.findByCategoryId(cid.get());
//			model.addAttribute("items", pro);
//		}
//		else {
//			Pageable page = PageRequest.of(p.orElse(0), 9);
//			Page<Product> list = productService.findAll(page);
//			model.addAttribute("items", list);
//		}
////		Pageable page = PageRequest.of(p.orElse(0), 9);
////		Page<Product> list = productService.findAll(page);
//		
//		return "shop/listShop";
//	}
	
	@RequestMapping("/seachPrice")
	public String search(Model model, @RequestParam("min") Optional<Double> min, 
			@RequestParam("max") Optional<Double> max,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);
		Page<Product> list = productService.findByUnitPriceBetween(minPrice, maxPrice, pageable);
		model.addAttribute("items", list);
		return "shop/listShop";
	}
	
	@RequestMapping("/search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("p") Optional<Integer> p) {
		Page<Product> list = null;
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		if(StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name, pageable);
		}
		else {
			list = productService.findAll(pageable);
		}
		model.addAttribute("items", list);
		return "shop/listShop";
	}
	
	@RequestMapping("/shopdesc")
	public String shopdesc(Model model,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> list = productService.findByProduct(pageable);
		model.addAttribute("items", list);
		return "shop/listShop";
	}
	
	@RequestMapping("/shopasc")
	public String shopasc(Model model,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> list = productService.findByProductAsc(pageable);
		model.addAttribute("items", list);
		return "shop/listShop";
	}
	
	@GetMapping("/shop")
    public String search(ModelMap model,
    @RequestParam(name = "name", required = false) String name,
    @RequestParam("page") Optional<Integer> page, 
    @RequestParam("size") Optional<Integer> size,
    @RequestParam("cid") Optional<Long> cid) {
       int currentPage = page.orElse(1); 
       int pageSize = size.orElse(9); 

       Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
       Page<Product> resultPage = null;

        if(cid.isPresent()) {
			resultPage = productService.findByCategoryId(cid.get(), pageable);
			model.addAttribute("items", resultPage);
		}
        else {
        	resultPage = productService.findAll(pageable);
        }
 
        int totalPages = resultPage.getTotalPages(); 
        if(totalPages > 0){
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if(totalPages > 5){
                if(end == totalPages) start = end - 5;
                else if(start == 1) end = start + 5;
            }

            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
            .boxed()
            .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("items", resultPage);
        return "shop/listShop";
    }
	
	
	@RequestMapping("/shop/detail/{productId}")
	public String detail(Model model, @PathVariable("productId") Long productId) {
		Product product = productService.findById(productId).get();
		model.addAttribute("item", product);
		return "shop/listShopDetail";
	}
}
