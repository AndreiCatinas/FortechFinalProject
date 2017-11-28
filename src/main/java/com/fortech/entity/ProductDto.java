package com.fortech.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

	private int id;
	private String category;
	private String model;

	@NotNull(message = "{field.null}")
	@Size(min = 2, max = 30, message = "{field.size}")
	private String name;

	private String aquisitionDate;

	@Size(min = 0, max = 30, message = "{field.size}")
	private String serialNumber;

	@Size(min = 0, max = 200, message = "{field.size}")
	private String description;

	public ProductDto() {

	}

	public ProductDto(Product product) {
		setId(product.getId());
		setCategory(product.getProductModel().getProductCategory().getCategory());
		setModel(product.getProductModel().getModel());
		setName(product.getName());
		setAquisitionDate(product.getAquisitionDate());
		setSerialNumber(product.getSerialNumber());
		setDescription(product.getDescription());
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(String aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductDto [category=" + category + ", model=" + model + ", name=" + name + ", aquisitionDate="
				+ aquisitionDate + ", serialNumber=" + serialNumber + ", description=" + description + "]";
	}

}
