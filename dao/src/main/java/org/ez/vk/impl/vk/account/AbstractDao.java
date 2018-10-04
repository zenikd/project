package org.ez.vk.impl.vk.account;

import java.util.Date;

import org.ez.api.dao.IAbstractDao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public abstract class AbstractDao<Entity, SearchDTO> implements IAbstractDao<Entity, SearchDTO> {
	public MongoCollection<BasicDBObject> collection;

	public BasicDBObject getBasicObject() {
		return new BasicDBObject("idReserve", "idBlock").append("dateReserve", new Date().getTime()).append("update",
				new Date().getTime());

	}
	
	
}
