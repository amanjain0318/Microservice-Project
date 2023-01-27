package com.aman.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.aman.productservice.model.Product;
import com.aman.productservice.service.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl ;
	
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Product> findProduct() {
    	log.info("Product fetching API invoked");
    		return productServiceImpl.findProduct(); 
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
    	log.info("Create Product API invoked");
    	productServiceImpl.createProduct(product);
    }

}
