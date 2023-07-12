/**
 * 
 */
package com.kagifzu.com.services;

import org.springframework.http.ResponseEntity;

import com.kagifzu.com.response.CategoryResponseRest;

/**
 * 
 */
public interface ICategoryService {
	public ResponseEntity<CategoryResponseRest> search();

	public ResponseEntity<CategoryResponseRest> searchById(Long id);
}
