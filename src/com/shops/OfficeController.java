package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mongodb.MongoServerException;
import com.mongodb.MongoWriteException;
import com.mysql.jdbc.CommunicationsException;

@ManagedBean
@SessionScoped
public class OfficeController {
	// member variables
	MongoDAO mDao;
	DAO dao;
	ArrayList<Office> offices;
	int isFound = 0;

	// no argument constructor
	public OfficeController() {
		super();
		try {
			// make instance of DAO
			mDao = new MongoDAO();
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// === member methods ===

	// return contents of list
	public ArrayList<Office> getOffices() {
		return offices;
	}

	// load stores from database
	public String loadOffices() {
		System.out.println("In loadOffices()");
		try {
			offices = mDao.loadOffices();
		} catch (MongoServerException e) {
			FacesMessage message = new FacesMessage("Error: Can not connect to MongoDB Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}// load

	// add new office to database
	public String addOffice(Office o) {
		System.out.println("Adding: " + o.getOfficeId() + " " + o.getOfficeLocation());

		// check if store ID exists is SQL database
		try {
			isFound = dao.checkStoreExists(o.getOfficeId());

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

		// if search was successful (result greater than zero)
		if (isFound > 0) {
			// add head office to MongoDB
			try {
				mDao.addOffice(o);
				return "officePage";

			} catch (MongoWriteException e) {
				FacesMessage message = new FacesMessage("Error: Office ID:" + o.getOfficeId() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;

			} catch (MongoServerException e) {
				FacesMessage message = new FacesMessage("Error: Can not connect to MongoDB Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			FacesMessage message = new FacesMessage("Error: Store ID:" + o.getOfficeId() + " Does not exist");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}

	}// add

	// delete selected office from database
	public void deleteOffice(int id) throws Exception {
		System.out.println("Delete ID: " + id);
		try {
			mDao.deleteOffice(id);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}// delete

}// class
