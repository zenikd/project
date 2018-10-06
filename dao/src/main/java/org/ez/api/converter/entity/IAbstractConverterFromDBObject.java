package org.ez.api.converter.entity;

import com.mongodb.BasicDBObject;

public interface IAbstractConverterFromDBObject<Entity> {
	public Entity convertDBObjectFromEntity(BasicDBObject basicDBObject);
}
