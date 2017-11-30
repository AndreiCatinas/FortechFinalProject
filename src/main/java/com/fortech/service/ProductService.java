package com.fortech.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Booking;
import com.fortech.entity.Product;
import com.fortech.entity.ProductCategory;
import com.fortech.entity.ProductDto;
import com.fortech.entity.ProductModel;
import com.fortech.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductModelService productModelService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private BookingService bookingService;

	public List<Product> getAllProducts(Integer id) {
		List<Product> products = new ArrayList<>();
		productRepository.findByProductModelId(id).forEach(products::add);
		return products;
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	public List<Product> getAllProducts(Boolean booked) {
		List<Product> products = new ArrayList<>();
		productRepository.findByBooked(booked).forEach(products::add);
		return products;
	}

	public Product getProduct(Integer id) {
		return productRepository.findOne(id);
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void addProduct(ProductDto productDto) throws ParseException {
		ProductModel productModel = productModelService.getProductModel(productDto.getModel());
		ProductCategory productCategory = productCategoryService.getProductCategory(productDto.getCategory());
		if (productCategory == null) {
			productCategory = new ProductCategory(productDto.getCategory());
			productCategoryService.addProductCategory(productCategory);
		}
		if (productModel == null) {
			productModel = new ProductModel(productDto.getModel(), productCategory);
			productModelService.addProductModel(productModel);
		}

		Product product = new Product();
		product.setSerialNumber(productDto.getSerialNumber());
		product.setAquisitionDate(productDto.getAquisitionDate());
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setProductModel(productModel);
		productRepository.save(product);
	}

	public void deleteProduct(Integer productId) {
		List<Booking> bookings = bookingService.getBookingsByProduct(productId);
		for (Booking b : bookings) {
			b.setProduct(null);
		}
		productRepository.delete(productId);
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	public void updateProduct(ProductDto productDto) throws ParseException {
		Product product = productRepository.findOne(productDto.getId());
		product.setAquisitionDate(productDto.getAquisitionDate());
		product.setDescription(productDto.getDescription());
		product.setName(productDto.getName());
		product.setSerialNumber(productDto.getSerialNumber());
		ProductModel productModel = productModelService.getProductModel(productDto.getModel());
		ProductCategory productCategory = productCategoryService.getProductCategory(productDto.getCategory());
		if (productCategory == null || !productCategory.getCategory().equals(productDto.getCategory())) {
			productCategory = new ProductCategory(productDto.getCategory());
			productCategoryService.addProductCategory(productCategory);
		}
		if (productModel == null || !productModel.getModel().equals(productDto.getModel())) {
			productModel = new ProductModel(productDto.getModel(), productCategory);
			productModelService.addProductModel(productModel);
		}
		product.setProductModel(productModel);
		productRepository.save(product);
	}
}
