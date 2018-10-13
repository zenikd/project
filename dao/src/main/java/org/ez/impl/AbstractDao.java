package org.ez.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ez.api.converter.entity.IAbstractConverterToDBObject;
import org.ez.api.dao.IAbstractDao;
import org.ez.vk.dao.common.entity.db.BaseEntity;
import org.ez.vk.dao.common.entity.search.SearchDTO;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;
import org.ez.vk.dao.common.helper.impl.JsonHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public abstract class AbstractDao<Entity> implements IAbstractDao<Entity, SearchDTO> {
	protected JsonHelper jsonHelper;
	public MongoCollection<BasicDBObject> collection;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public AbstractDao() {
		jsonHelper = new JsonHelper<Entity>();
	}

	public List<Entity> select(SearchDTO searchDTO) throws InternalException {
		BasicDBObject searchQuery = searchDTO.getSearchQuery().getQuery();
		List<BasicDBObject> searchResult = collection.find(searchQuery).limit(searchDTO.getLimit())
				.into(new ArrayList<BasicDBObject>());
		return convetJsonToEntity(searchResult);
	}

	public void addEntity(Entity entity) throws RootUserException, InternalException {
		setDefaultEntityParam(entity);
		BasicDBObject dbObject = jsonHelper.entityToDBObject((BaseEntity) entity);
		collection.insertOne(dbObject);
	}

	protected void setDefaultEntityParam(Entity entity) throws InternalException {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setUpdate(getEndPrivDay());
	}

	protected long getEndDateCurDay() throws InternalException {
		String curDate = dateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(curDate));

		} catch (ParseException e) {
			throw new InternalException();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.HOUR, 3);
		return c.getTime().getTime();
	}

	protected long getEndPrivDay() throws InternalException {
		String curDate = dateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(curDate));
			c.add(Calendar.HOUR, 3);

		} catch (ParseException e) {
			throw new InternalException();
		}
		return c.getTime().getTime();
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
