package org.ez.vk.impl.vk.account;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;

public class Test {

	public static void main(String[] args) {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds121673.mlab.com:21673/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		database.createCollection("town",
		          new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));
		

	}

}
