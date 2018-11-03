package org.ez.vk.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Test {

	public static void main(String[] args) {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds159812.mlab.com:59812/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		MongoCollection<BasicDBObject> collection = database.getCollection("account", BasicDBObject.class);
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.append("id", "2");

		for (int i = 0; i < 100; i++) {
			collection.find(basicDBObject);
		}
	}

}
