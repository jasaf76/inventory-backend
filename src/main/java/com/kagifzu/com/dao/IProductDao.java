package com.kagifzu.com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import com.kagifzu.com.model.Product;

@RestController
public interface IProductDao extends CrudRepository<Product, Long> {

	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByNameLike(String name);

	List<Product> findByNameContainingIgnoreCase(String name);

}
