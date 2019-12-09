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
public class StoreController {
	// member variables
	DAO dao;
	ArrayList<Store> stores;

	// no argument constructor
	public StoreController() {
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
	public ArrayList<Store> getStores() {
		return stores;
	}// get

	// load stores from database
	public String loadStores() {
		System.out.println("In loadStores()");
		try {
			stores = dao.loadStores();

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

	// add new store to database
	public String addStore(Store s) {
		System.out.println("Adding: " + s.getStoreName() + " " + s.getStoreFounded());
		try {
			dao.addStore(s);
			return "storesPage";

		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: " + s.getStoreName() + " already exists");
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

	// delete selected store from database
	public void deleteStore(int id) throws SQLException {
		System.out.println("Delete ID: " + id);
		try {
			dao.deleteStore(id);

		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(
					"Error - Store ID: " + id + " has not been deleted from MySQL, it contains products");
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
	}// delete

}// class
