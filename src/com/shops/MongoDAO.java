package com.shops;

import java.util.ArrayList;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	// member variables
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";

	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	// constructor
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}

	// === OFFICE METHODS ===

	// load offices
	public ArrayList<Office> loadOffices() throws Exception {
		// local variables
		ArrayList<Office> offices = new ArrayList<Office>();
		FindIterable<Document> headOffices = collection.find();

		// loop through document and make list
		for (Document d : headOffices) {
			Office o = new Office();

			o.setOfficeId((int) (d.get("_id")));
			o.setOfficeLocation((String) (d.get("location")));
			offices.add(o);
		}

		return offices;
	}// load

	// add new office
	public void addOffice(Office office) throws Exception {
		// create document with office object
		Document myDoc = new Document();
		myDoc.append("_id", office.getOfficeId()).append("location", office.getOfficeLocation());

		// insert document
		collection.insertOne(myDoc);
	}// add

	// delete office
	public void deleteOffice(int id) throws Exception {

		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		collection.deleteOne(query);

	}// delete

}// class
