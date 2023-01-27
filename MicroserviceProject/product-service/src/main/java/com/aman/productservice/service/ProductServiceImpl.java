package com.aman.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.productservice.model.Product;
import com.aman.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public int createProduct(Product product) {
		
		productRepository.save(product);
		log.info("Product created with Id {}"+product.getProductID());
		return product.getProductID();
	}

	@Override
	public List<Product> findProduct() {
		
		return productRepository.findAll();
	}
}
