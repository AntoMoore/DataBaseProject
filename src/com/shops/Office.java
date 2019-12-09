package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Office {
	// member variables
	private int officeId;
	private String officeLocation;

	// gets/sets
	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

}// class
