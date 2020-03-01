package org.ez.vk.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.ez.vk.entity.db.BaseEntity;
import org.ez.vk.entity.db.constant.BaseEntityConst;
import org.ez.vk.entity.query.DBQuery;
import org.ez.vk.entity.query.BaseSearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.UpdateDTOQuery;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.helper.DateHelper;
import org.ez.vk.helper.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

public abstract class AbstractDao<Entity> implements org.ez.vk.db.AbstractDao<Entity, BaseSearchDTOQuery> {
	protected JsonHelper jsonHelper;
	public MongoCollection<BasicDBObject> collection;
	@Autowired
	protected DateHelper dateHelper;

	public AbstractDao() {
		jsonHelper = new JsonHelper<Entity>();
		setCollection();

	}

	public List<Entity> select(BaseSearchDTOQuery searchDTO) throws InternalException {
		BasicDBObject searchQuery = searchDTO.getSearchQuery().getQuery();
		List<BasicDBObject> searchResult = collection.find(searchQuery).limit(searchDTO.getLimit())
				.into(new ArrayList<BasicDBObject>());
		return convetListJsonToEntity(searchResult);
	}

	public void addEntity(Entity entity) throws InternalException, RootUserException {
		BasicDBObject dbObject = jsonHelper.entityToDBObject((BaseEntity) entity);
		collection.insertOne(dbObject);
	}

	public void addListEntity(List<Entity> entities) throws InternalException, RootUserException {
		List<BasicDBObject> document = new ArrayList();
		for (Entity entity : entities) {
			document.add(jsonHelper.entityToDBObject((BaseEntity) entity));
		}
		collection.insertMany(document);
	}
	
	public Integer updateEntity(UpdateDTOQuery updateFullQuery) {
		Integer limit = updateFullQuery.getLimit();
		for (int count = 0; count < limit; count++) {
			BasicDBObject searchQuery = updateFullQuery.getSearchQuery().getQuery();
			BasicDBObject updateQuery = updateFullQuery.getUpdateQuery().getQuery();
			UpdateResult result = collection.updateOne(searchQuery, updateQuery);
			if(result.getModifiedCount() == 0) return count;
		}
		return limit;
	}
	
	
	public void updateEntity(Entity entity) throws InternalException {
		BaseEntity baseEntity = (BaseEntity) entity;
		DBQuery idQuery = new DBQuery();
		idQuery.addQueryParam(BaseEntityConst._ID, Operators.$EQ, baseEntity.getObjectId());		
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append(Operators.$SET, jsonHelper.entityToDBObject(baseEntity));
		collection.updateOne(idQuery.getQuery(), updateQuery);
	}
	
	

	protected List<Entity> convetListJsonToEntity(List<BasicDBObject> resultSearchJson) throws InternalException {
		List<Entity> listResultEntity = new ArrayList();
		for (BasicDBObject json : resultSearchJson) {
			listResultEntity.add((Entity) jsonHelper.bsonToEntity(json, getEntityInstance()));
		}
		return listResultEntity;
	}

	protected abstract Entity getEntityInstance();

	protected abstract void setCollection();
}
