package com.kagifzu.com.services;

import org.springframework.http.ResponseEntity;

import com.kagifzu.com.model.Product;
import com.kagifzu.com.response.ProductResponseRest;

public interface IProductService {

	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

	public ResponseEntity<ProductResponseRest> searchById(Long Id);

	public ResponseEntity<ProductResponseRest> searchByName(String Name);
}
