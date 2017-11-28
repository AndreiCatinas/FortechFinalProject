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

	@RequestMapping(method = RequestMethod.GET, value = "/categories")
	public List<ProductCategory> getAllProductCategory() {
		return productCategoryService.getAllProductCategories();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/categories/{id}")
	public ProductCategory getProductCategory(@PathVariable Integer id) {
		return productCategoryService.getProductCategory(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/categories")
	public void addProductCategory(@RequestBody ProductCategory productCategory) {
		productCategoryService.addProductCategory(productCategory);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/categories/{id}")
	public void updateProductCategory(@RequestBody ProductCategory productCategory, Integer id) {
		productCategoryService.updateProductCategory(productCategory);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/categories/{id}")
	public void deleteProductCategory(@PathVariable Integer id) {
		productCategoryService.deleteProductCategory(id);
	}
}
