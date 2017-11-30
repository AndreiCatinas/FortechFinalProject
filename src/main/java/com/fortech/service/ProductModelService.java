package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.ProductModel;
import com.fortech.repository.ProductModelRepository;

@Service
public class ProductModelService {

	@Autowired
	private ProductModelRepository productModelRepository;

	public List<ProductModel> getAllProductModel(Integer id) {
		List<ProductModel> productModels = new ArrayList<>();
		productModelRepository.findByProductCategoryId(id).forEach(productModels::add);
		return productModels;
	}
	
	public List<ProductModel> getAllProductModel() {
		List<ProductModel> productModels = new ArrayList<>();
		productModelRepository.findAll().forEach(productModels::add);
		return productModels;
	}

	public ProductModel getProductModel(Integer id) {
		return productModelRepository.findOne(id);
	}
	
	public ProductModel getProductModel(String model) {
		return productModelRepository.findByModel(model);
	}

	public void addProductModel(ProductModel productModel) {
		productModelRepository.save(productModel);
	}

	public void updateProductModel(ProductModel productModel) {
		productModelRepository.save(productModel);
	}

	public void deleteProductModel(Integer id) {
		productModelRepository.delete(id);
	}
}
