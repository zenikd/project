package org.ez.vk.db.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.ez.vk.db.GroupDao;
import org.ez.vk.entity.db.GroupEntity;
import org.springframework.stereotype.Service;

@Service
public class GroupDaoImpl extends AbstractDao<GroupEntity> implements GroupDao {
	@Override
	protected GroupEntity getEntityInstance() {
		return new GroupEntity();
	}

	@Override
	protected void setCollection() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds121673.mlab.com:21673/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("group", BasicDBObject.class);

	}

}
