package com.fortech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "product_category")
@DynamicInsert
public class ProductCategory {

	@Id
	@GeneratedValue
	private int id;

	private String category;

	public ProductCategory() {

	}
	
	public ProductCategory(String category) {
		this.category = category;
	}

	public ProductCategory(Integer id, String category) {
		this.id = id;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return this.category;
	}

}
