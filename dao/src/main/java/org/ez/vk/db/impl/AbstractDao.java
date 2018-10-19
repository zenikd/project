package org.ez.vk.db.impl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import org.ez.vk.entity.db.BaseEntity;
import org.ez.vk.entity.query.search.FullSearchQuery;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.helper.DateHelper;
import org.ez.vk.helper.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<Entity> implements org.ez.vk.db.AbstractDao<Entity, FullSearchQuery>
{
	protected JsonHelper jsonHelper;
	public MongoCollection<BasicDBObject> collection;
	@Autowired
	protected DateHelper dateHelper;

	public AbstractDao() {
		jsonHelper = new JsonHelper<Entity>();
	}

	public List<Entity> select(FullSearchQuery searchDTO) throws InternalException {
		BasicDBObject searchQuery = searchDTO.getSearchQuery().getQuery();
		List<BasicDBObject> searchResult = collection.find(searchQuery).limit(searchDTO.getLimit())
				.into(new ArrayList<BasicDBObject>());
		return convetJsonToEntity(searchResult);
	}

	public void addEntity(Entity entity) throws InternalException , RootUserException
	{
		BasicDBObject dbObject = jsonHelper.entityToDBObject((BaseEntity) entity);
		collection.insertOne(dbObject);
	}

	public void addListEntity(List<Entity> entities) throws  InternalException , RootUserException {
		List<BasicDBObject> document= new ArrayList();
		for (Entity entity : entities) {
			document.add(jsonHelper.entityToDBObject((BaseEntity) entity));
		}
		collection.insertMany(document);
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
