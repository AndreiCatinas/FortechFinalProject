package com.fortech.repository;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {

	public ProductCategory findByCategory(String category);
}
