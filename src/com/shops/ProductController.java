package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean
@SessionScoped
public class ProductController {
	// member variables
	DAO dao;
	ArrayList<Product> products;

	// no argument constructor
	public ProductController() {
		super();
		try {
			// make instance of DAO
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// === member methods ===

	// return contents of list
	public ArrayList<Product> getProducts() {
		return products;
	}

	// load stores from database
	public String loadProducts() {
		System.out.println("In loadProducts()");
		try {
			products = dao.loadProducts();

		} catch (CommunicationsException e) {
			System.out.println("ERROR: Database Offline");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (SQLException e) {
			System.out.println("ERROR: Can Not Load Data");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}// load

	// delete selected store from database
	public void deleteProduct(int id) throws SQLException {
		System.out.println("Delete ID: " + id);
		try {
			dao.deleteProduct(id);
		} catch (CommunicationsException e) {
			System.out.println("ERROR: Database Offline");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (SQLException e) {
			System.out.println("ERROR: Can Not Load Data");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}// delete

	// add new product to database
	public String addProduct(Product p) {
		System.out.println("Adding: " + p.getStoreId() + " " + p.getProductName() + " " + p.getProductPrice());
		try {
			dao.addProduct(p);
			return "productsPage";

		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store ID " + p.getStoreId() + " does not exist");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (CommunicationsException e) {
			System.out.println("ERROR: Database Offline");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (SQLException e) {
			System.out.println("ERROR: Can Not Load Data");
			FacesMessage message = new FacesMessage("Error: Can not connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}// add

}// class
