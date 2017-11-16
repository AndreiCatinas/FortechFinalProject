package com.fortech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.entity.Product;
import com.fortech.entity.ProductCategory;
import com.fortech.entity.ProductModel;
import com.fortech.service.ProductService;

// >>>>>>>>>>>check delete update methods <<<<<<<<<
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, value = "/category/{categoryId}/model/{modelId}/product")
	public List<Product> getAllProducts(@PathVariable Integer modelId) {
		return productService.getAllProducts(modelId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "category/{categoryId}/model/{modelId}/product/{id}")
	public Product getProduct(@PathVariable Integer id) {
		return productService.getProduct(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/category/{categoryId}/model/{modelId}/product")
	public void addProduct(@RequestBody Product product, @PathVariable Integer categoryId,
			@PathVariable Integer modelId) {
		ProductModel productModel = new ProductModel(modelId, "");
		productModel.setProductCategory(new ProductCategory(categoryId, ""));
		product.setProductModel(productModel);
		productService.addProduct(product);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/category/{categoryId}/model/{modelId}/product/{id}")
	public void updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		productService.updateProduct(productService.getProduct(id), product);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/category/{categoryId}/model/{modelId}/product/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}
}
