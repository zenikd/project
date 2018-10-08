package org.ez.impl;

import java.util.ArrayList;
import java.util.List;

import org.ez.api.converter.entity.IGroupToDBObject;
import org.ez.api.dao.IGroupDao;
import org.ez.entity.vk.db.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
@Service
public class GroupDao implements IGroupDao {
	public MongoCollection<BasicDBObject> collection;
	@Autowired
	IGroupToDBObject groupToDBObject;
	
	public GroupDao() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds121673.mlab.com:21673/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("group", BasicDBObject.class);
	}
	
	public void addGroups(List<GroupEntity> groups) {
		List<BasicDBObject> documents = getListDocument( groups);
		collection.insertMany(documents);
	}
	
	private List<BasicDBObject> getListDocument(List<GroupEntity> groups){
		List<BasicDBObject> documents = new ArrayList<BasicDBObject>();
		for(GroupEntity group: groups) {
			documents.add(groupToDBObject.convertEntityToDBObject(group));
		}
		return documents;
	}
}
