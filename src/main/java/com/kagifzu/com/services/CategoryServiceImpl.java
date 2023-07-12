/**
 * 
 */
package com.kagifzu.com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kagifzu.com.dao.ICategoryDao;
import com.kagifzu.com.model.Category;
import com.kagifzu.com.response.CategoryResponseRest;

/**
 * 
 */
@Service

public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		// TODO Auto-generated method stub
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();

			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Response ist ok", "00", "Die Response war erfolgreich");

		} catch (Exception e) {
			response.setMetadata("Response ist not ok", "-1", "Die Response war FAILED");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		// TODO Auto-generated method stub
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> category = categoryDao.findById(id);
			if (category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Response ist  ok", "-1", "Die Kategorie wurde  gefunden");
			} else {
				response.setMetadata("Response ist not ok", "-1", "Die Kategorie wurde nicht gefunden");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Response ist not ok", "-1", "Die Response war FAILED per Id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
