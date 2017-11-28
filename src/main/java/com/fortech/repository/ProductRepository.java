package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public List<Product> findByProductModelId(Integer id);

}
