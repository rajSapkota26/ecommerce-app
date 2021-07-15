package com.mbs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String userId;	
	private String productName;
	private Long productId;
	private String productPrice;	
	private int quantities;
	private String productImage;
	private String productDiscount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	
	public int getQuantities() {
		return quantities;
	}
	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}
	
	
	

}
