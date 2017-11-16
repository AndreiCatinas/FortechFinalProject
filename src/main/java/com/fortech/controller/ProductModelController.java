package com.fortech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.entity.ProductCategory;
import com.fortech.entity.ProductModel;
import com.fortech.service.ProductModelService;

@RestController
public class ProductModelController {

	@Autowired
	private ProductModelService productModelService;

	@RequestMapping(method = RequestMethod.GET, value = "/category/{id}/model")
	public List<ProductModel> getAllProductModel(@PathVariable Integer id) {
		return productModelService.getAllProductModel(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/category/{categoryId}/model/{modelId}")
	public ProductModel getProductModel(@PathVariable Integer modelId) {
		return productModelService.getProductModel(modelId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/category/{categoryId}/model")
	public void addProductModel(@RequestBody ProductModel productModel, @PathVariable Integer categoryId) {
		productModel.setProductCategory(new ProductCategory(categoryId, ""));
		productModelService.addProductModel(productModel);
	}

}
