package org.ez.api.converter;

import com.mongodb.BasicDBObject;

public interface IAbstractConverterFromDBObject<Entity> {
	public Entity convertDBObjectFromEntity(BasicDBObject basicDBObject,  Entity entity);
}
