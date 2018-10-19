package org.ez.impl;

import org.ez.api.dao.IGroupDao;
import org.ez.vk.dao.common.entity.db.GroupEntity;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class GroupDao extends AbstractDao<GroupEntity> implements IGroupDao {
	public GroupDao() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds121673.mlab.com:21673/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("group", BasicDBObject.class);
	}

	@Override
	protected GroupEntity getEntityInstance() {
		return new GroupEntity();
	}

}
