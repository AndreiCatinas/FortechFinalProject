package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.ProductCategory;
import com.fortech.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	public List<ProductCategory> getAllProductCategories() {
		List<ProductCategory> productCategories = new ArrayList<>();
		productCategoryRepository.findAll().forEach(productCategories::add);
		return productCategories;
	}

	public ProductCategory getProductCategory(Integer id) {
		return productCategoryRepository.findOne(id);
	}

	public void addProductCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}

	public void updateProductCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}

	public void deleteProductCategory(Integer id) {
		productCategoryRepository.delete(id);
	}

}
