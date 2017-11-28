package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.ProductModel;

public interface ProductModelRepository extends CrudRepository<ProductModel, Integer> {

	public List<ProductModel> findByProductCategoryId(Integer id);
}
