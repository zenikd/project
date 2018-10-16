package org.ez.impl;

import java.util.ArrayList;
import java.util.List;

import org.ez.api.dao.IAbstractDao;
import org.ez.vk.dao.common.entity.db.BaseEntity;
import org.ez.vk.dao.common.entity.search.SearchDTO;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;
import org.ez.vk.dao.common.helper.DateHelper;
import org.ez.vk.dao.common.helper.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public abstract class AbstractDao<Entity> implements IAbstractDao<Entity, SearchDTO> {
	protected JsonHelper jsonHelper;
	public MongoCollection<BasicDBObject> collection;
	@Autowired
	protected DateHelper dateHelper;

	public AbstractDao() {
		jsonHelper = new JsonHelper<Entity>();
	}

	public List<Entity> select(SearchDTO searchDTO) throws InternalException {
		BasicDBObject searchQuery = searchDTO.getSearchQuery().getQuery();
		List<BasicDBObject> searchResult = collection.find(searchQuery).limit(searchDTO.getLimit())
				.into(new ArrayList<BasicDBObject>());
		return convetJsonToEntity(searchResult);
	}

	public void addEntity(Entity entity) throws InternalException , RootUserException {
		setDefaultEntityParam(entity);
		BasicDBObject dbObject = jsonHelper.entityToDBObject((BaseEntity) entity);
		collection.insertOne(dbObject);
	}

	public void addListEntity(List<Entity> entities) throws  InternalException , RootUserException {
		List<BasicDBObject> document= new ArrayList();
		for (Entity entity : entities) {
			setDefaultEntityParam(entity);
			document.add(jsonHelper.entityToDBObject((BaseEntity) entity));
		}
		collection.insertMany(document);
	}

	protected void setDefaultEntityParam(Entity entity) throws InternalException {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setUpdated(dateHelper.getEndPrivDay());
	}

	

	protected List<Entity> convetJsonToEntity(List<BasicDBObject> resultSearchJson) throws InternalException {
		List<Entity> listResultEntity = new ArrayList();
		for (BasicDBObject json : resultSearchJson) {
			listResultEntity.add((Entity) jsonHelper.bsonToEntity(json, getEntityInstance()));
		}
		return listResultEntity;
	}

	protected abstract Entity getEntityInstance();
}
