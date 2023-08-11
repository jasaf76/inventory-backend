/**
 * 
 */
package com.kagifzu.com.services;

import org.springframework.http.ResponseEntity;

import com.kagifzu.com.model.Category;
import com.kagifzu.com.response.CategoryResponseRest;

/**
 * 
 */
public interface ICategoryService {
	public ResponseEntity<CategoryResponseRest> search();

	public ResponseEntity<CategoryResponseRest> searchById(Long id);

	public ResponseEntity<CategoryResponseRest> save(Category category);

	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);

	public ResponseEntity<CategoryResponseRest> deleteById(Long id);

}
