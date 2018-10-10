package org.ez.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.CharSetUtils;
import org.ez.api.converter.entity.IAbstractConverterToDBObject;
import org.ez.api.dao.IAbstractDao;
import org.ez.vk.dao.common.entity.db.BaseEntity;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;

public abstract class AbstractDao<Entity, SearchDTO> implements IAbstractDao<Entity, SearchDTO> {
	public MongoCollection<BasicDBObject> collection;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	protected void setDefaultEntityParam(Entity entity) throws InternalException {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setUpdate(getEndPrivDay());
	}

	public void addEntity(String jsonData) throws RootUserException, InternalException {
		BasicDBObject dbObject = jsonToDbObject(jsonData);
		collection.insertOne(dbObject);
	}

	protected List<Entity> convertJsonToEntity(List<BasicDBObject> listDocumnet,
			IAbstractConverterToDBObject<Entity> conveter) {
		List<Entity> listEntities = new ArrayList<Entity>();
		try {
			for (BasicDBObject documnetJson : listDocumnet) {
				Entity entity = (Entity) conveter.convertEntityToDBObject((Entity) documnetJson);
				listEntities.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listEntities;
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

	private BasicDBObject jsonToDbObject(String jsonData){
		return (BasicDBObject) JSON.parse(CharSetUtils.delete(jsonData, "\t\r\n\b"));
	}

}
