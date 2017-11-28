package com.fortech.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_model")
public class ProductModel {

	@Id
	@GeneratedValue
	private int id;

	private String model;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private ProductCategory productCategory;

	public ProductModel() {

	}

	public ProductModel(Integer id, String model) {
		this.id = id;
		this.model = model;
	}
	
	public ProductModel(String model, ProductCategory productCategory) {
		this.model = model;
		this.productCategory = productCategory;
	}

	public ProductModel(String model, Integer productCategoryId) {
		this.model = model;
		this.productCategory = new ProductCategory(productCategoryId, "");
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		return this.model;
	}

}
