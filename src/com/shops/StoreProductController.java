package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean
@SessionScoped
public class StoreProductController {
	// member variables
	DAO dao;
	ArrayList<StoreProduct> storeProducts;

	// no argument constructor
	public StoreProductController() {
		super();
		try {
			// make instance of DAO
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// === member methods ===

	// return contents of list
	public ArrayList<StoreProduct> getStoreProducts() {
		return storeProducts;
	}

	// load storesProducts from database
	public String loadStoreProducts(int id) {
		System.out.println("In loadStoreProducts(int)");
		try {
			System.out.println("Show Products ID: " + id);
			storeProducts = dao.loadStoreProducts(id);
			return "storeProductsPage";

		} catch (CommunicationsException e) {
			System.out.println("ERROR: Database Offline");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (SQLException e) {
			System.out.println("ERROR: Can Not Load Data");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}// load

}// class
