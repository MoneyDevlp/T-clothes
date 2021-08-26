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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.shop.bean.Product;
import com.poly.shop.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("user")
	public String homeUser(ModelMap model,
		    @RequestParam(name = "name", required = false) String name,
		    @RequestParam("page") Optional<Integer> page, 
		    @RequestParam("size") Optional<Integer> size,
		    @RequestParam("cid") Optional<Long> cid) {
		   int currentPage = page.orElse(1);
	       int pageSize = size.orElse(12); 

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
	        return "views/home";
	}
	
	@RequestMapping("admin")
	public String homeAdmin() {
		return "redirect:/assert/admin/index.html";
	}
}
