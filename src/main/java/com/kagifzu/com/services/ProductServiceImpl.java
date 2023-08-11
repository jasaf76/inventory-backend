package com.kagifzu.com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kagifzu.com.dao.ICategoryDao;
import com.kagifzu.com.dao.IProductDao;
import com.kagifzu.com.model.Category;
import com.kagifzu.com.model.Product;
import com.kagifzu.com.response.ProductResponseRest;
import com.kagifzu.com.util.Util;

@Service
public class ProductServiceImpl implements IProductService {
	private ICategoryDao categoryDao;
	private IProductDao productDao;

	public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();

		try {
			// search category to set in the product object
			Optional<Category> category = categoryDao.findById(categoryId);

			if (category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("request FAILED", "-1", "keine Asoziacion Gefunden");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			// save the front
			Product productSaved = productDao.save(product);
			if (productSaved != null) {
				list.add(productSaved);
				response.getProduct().setProductcs(list);
				response.setMetadata("request OK", "00", "Kategorie wurde gefunden");
			} else {
				response.setMetadata("request Failed", "-1", "Die Kategorie wurde nicht gefunden");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setMetadata("request Failed", "-1", "error Product not saved");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchById(Long id) {
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();

		try {
			// search product by id
			Optional<Product> product = productDao.findById(id);

			if (product.isPresent()) {

				byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
				product.get().setPicture(imageDescompressed);
				list.add(product.get());
				response.getProduct().setProductcs(list);
				response.setMetadata("request ist cool ", "00", "es wurde ein Produckt gefunden");
			} else {
				response.setMetadata("request FAILED", "-1", "kein Produkt gefunden");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setMetadata("request Failed", "-1", "error Product not saved");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchByName(String name) {
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();

		try {
			// search product by name
			listAux = productDao.findByNameContainingIgnoreCase(name);

			if (listAux.size() > 0) {

				listAux.stream().forEach(product -> {
					byte[] imageDescompressed = Util.decompressZLib(product.getPicture());
					product.setPicture(imageDescompressed);
					list.add(product);
				});

				response.getProduct().setProductcs(list);
				response.setMetadata("request ist cool ", "00",
						String.format("Mögliche Produkte gefunden %d", listAux.size()));
			} else {
				response.setMetadata("request FAILED", "-1", "kein Produkt gefunden mit dieser Bezeihnung");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setMetadata("request Failed", "-1", "error Product not saved");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

}
