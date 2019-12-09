package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class StoreProduct {
	// member variables
	private int storeId;
	private String storeName;
	private String storeFounded;
	private int productId;
	private String productName;
	private float productPrice;

	// gets/sets
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreFounded() {
		return storeFounded;
	}

	public void setStoreFounded(String storeFounded) {
		this.storeFounded = storeFounded;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

}// class
