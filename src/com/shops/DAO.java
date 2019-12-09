package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	// member variables
	private DataSource mysqlDS;

	// null constructor
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	// === STORE METHODS ===

	// load stores
	public ArrayList<Store> loadStores() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Store> stores = new ArrayList<Store>();

		// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setStoreId(myRs.getInt("id"));
			s.setStoreName(myRs.getString("name"));
			s.setStoreFounded(myRs.getString("founded"));
			stores.add(s);
		}

		// close statements and connections
		myStmt.close();
		myConn.close();

		return stores;
	}// load

	// add new store
	public void addStore(Store store) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "insert into store (name, founded) values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, store.getStoreName());
		myStmt.setString(2, store.getStoreFounded());
		myStmt.execute();

		// close statements and connections
		myStmt.close();
		myConn.close();
	}// add

	// delete store
	public void deleteStore(int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, id);
		myStmt.execute();

		// close statements and connections
		myStmt.close();
		myConn.close();
	}// delete

	// === PRODUCT METHODS ===

	// load products
	public ArrayList<Product> loadProducts() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setProductId(myRs.getInt("pid"));
			p.setStoreId(myRs.getInt("sid"));
			p.setProductName(myRs.getString("prodName"));
			p.setProductPrice(myRs.getFloat("price"));
			products.add(p);
		}

		// close statements and connections
		myStmt.close();
		myConn.close();

		return products;
	}// load

	// delete product
	public void deleteProduct(int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from product where pid = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, id);
		myStmt.execute();

		// close statements and connections
		myStmt.close();
		myConn.close();

	}// delete

	// add new store
	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "insert into product (sid, prodName, price) values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getStoreId());
		myStmt.setString(2, product.getProductName());
		myStmt.setFloat(3, product.getProductPrice());
		myStmt.execute();

		// close statements and connections
		myStmt.close();
		myConn.close();
	}// add

	// === STORE/PRODUCT METHODS ===

	// load store products
	public ArrayList<StoreProduct> loadStoreProducts(int id) throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "SELECT s.id, s.name, s.founded, p.pid, p.prodName, p.price " + "FROM store s "
				+ "INNER JOIN product p " + "on s.id = p.sid " + "WHERE s.id = " + id + "";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();

		// process result set
		while (myRs.next()) {
			StoreProduct sp = new StoreProduct();
			sp.setStoreId(myRs.getInt("id"));
			sp.setStoreName(myRs.getString("name"));
			sp.setStoreFounded(myRs.getString("founded"));
			sp.setProductId(myRs.getInt("pid"));
			sp.setProductName(myRs.getString("prodName"));
			sp.setProductPrice(myRs.getFloat("price"));
			storeProducts.add(sp);
		}

		// close statements and connections
		myStmt.close();
		myConn.close();

		return storeProducts;
	}// load

	// === OFFICE METHODS ===
	public int checkStoreExists(int search) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		int size = 0;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store where id = " + search + "";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		if (myRs != null) {
			myRs.last(); // moves cursor to the last row
			size = myRs.getRow(); // get row id
		}

		// close statements and connections
		myStmt.close();
		myConn.close();

		return size;
	}// check

}// class
