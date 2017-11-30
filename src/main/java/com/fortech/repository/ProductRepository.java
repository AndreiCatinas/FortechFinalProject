package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fortech.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	public List<Product> findByProductModelId(Integer id);
	public List<Product> findByBooked(Boolean booked);

}
