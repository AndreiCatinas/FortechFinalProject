package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Product;
import com.fortech.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts(Integer id) {
		List<Product> products = new ArrayList<>();
		productRepository.findByProductModelId(id).forEach(products::add);
		return products;
	}

	public Product getProduct(Integer id) {
		return productRepository.findOne(id);
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(Integer id) {
		productRepository.delete(id);
	}

	public void updateProduct(Product oldProduct, Product newProduct) {
		if (oldProduct.getSerialNumber() != null) {
			newProduct.setSerialNumber(oldProduct.getSerialNumber());
		}
		if (oldProduct.getDescription() != null) {
			newProduct.setDescription(oldProduct.getDescription());
		}
		if (oldProduct.getAquisitionDate() != null) {
			newProduct.setAquisitionDate(oldProduct.getAquisitionDate());
		}
		newProduct.setExtendLimit(oldProduct.getExtendLimit());
		productRepository.save(newProduct);
	}
}
