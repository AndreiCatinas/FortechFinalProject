package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fortech.entity.ProductModel;

@Repository
public interface ProductModelRepository extends CrudRepository<ProductModel, Integer> {

	public List<ProductModel> findByProductCategoryId(Integer id);
	public ProductModel findByModel(String model);
}
