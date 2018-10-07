package org.ez.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

public class Test {

	public static void main(String[] args) {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds159812.mlab.com:59812/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		MongoCollection<BasicDBObject>  collection = database.getCollection("account", BasicDBObject.class);
		
		BasicDBObject queryDoc = new BasicDBObject();
		queryDoc.append("type",  "workingg");
		
		BasicDBObject updateDoc = new BasicDBObject();
		updateDoc.append("userName",  "Евгений зеньков");
		BasicDBObject set = new BasicDBObject("$set", updateDoc);
		UpdateResult listDocumnet = collection.updateOne(queryDoc, set);
		int a = 1;
		a++;

	}

}
