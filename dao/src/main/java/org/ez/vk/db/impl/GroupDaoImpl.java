package org.ez.vk.db.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import org.ez.vk.converter.GroupFromDBObject;
import org.ez.vk.db.GroupDao;
import org.ez.vk.entity.db.GroupEntity;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.entity.query.BaseSearchDTOQuery;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.NotUniqueException;
import org.ez.vk.exception.user.RootUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupDaoImpl extends AbstractDao<GroupEntity> implements GroupDao {
	@Autowired
	GroupFromDBObject groupFromDBObject;

	@Override
	protected GroupEntity getEntityInstance() {
		return new GroupEntity();
	}

	@Override
	protected void setCollection() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds159812.mlab.com:59812/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("groups", BasicDBObject.class);
	}

	public void addEntity(GroupEntity groupEntity) throws InternalException, RootUserException {
		super.addEntity(groupEntity);
	}

	@Override
	protected List<GroupEntity> convetListJsonToEntity(List<BasicDBObject> resultSearchJson) throws InternalException {
		List<GroupEntity> listGroups = new ArrayList();
		for (BasicDBObject json : resultSearchJson) {
			listGroups.add(groupFromDBObject.convertDBObjectFromEntity(json));
		}
		return listGroups;
	}
}
