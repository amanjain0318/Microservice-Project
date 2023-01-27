package com.aman.productservice.service;

import java.util.List;

import com.aman.productservice.model.Product;

public interface ProductService {

	public List<Product> findProduct();

	public int createProduct(Product product);
}
