package com.fortech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "product")
@DynamicInsert
public class Product {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "aquisition_date")
	private String aquisitionDate;

	private String description;

	@Column(name = "extend_limit")
	private int extendLimit;

	private Boolean booked;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	private ProductModel productModel;

	public Product() {

	}

	public Product(int id, String name, String serialNumber, String aquisitionDate, String description,
			int extendLimit) {
		super();
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.aquisitionDate = aquisitionDate;
		this.description = description;
		this.extendLimit = extendLimit;
	}

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(String aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExtendLimit() {
		return extendLimit;
	}

	public void setExtendLimit(int extendLimit) {
		this.extendLimit = extendLimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
