package com.fortech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.entity.ProductCategory;
import com.fortech.service.ProductCategoryService;

@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(method = RequestMethod.GET, value = "/category")
	public List<ProductCategory> getAllProductCategory() {
		return productCategoryService.getAllProductCategories();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/category/{id}")
	public ProductCategory getProductCategory(@PathVariable Integer id) {
		return productCategoryService.getProductCategory(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/category")
	public void addProductCategory(@RequestBody ProductCategory productCategory) {
		productCategoryService.addProductCategory(productCategory);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/category/{id}")
	public void updateProductCategory(@RequestBody ProductCategory productCategory, Integer id) {
		productCategoryService.updateProductCategory(productCategory);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/category/{id}")
	public void deleteProductCategory(@PathVariable Integer id) {
		productCategoryService.deleteProductCategory(id);
	}
}
