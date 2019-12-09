package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {
	// member variables
	private int storeId;
	private String storeName;
	private String storeFounded;

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

}// class
