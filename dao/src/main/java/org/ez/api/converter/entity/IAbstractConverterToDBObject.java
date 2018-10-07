package org.ez.api.converter.entity;

import com.mongodb.BasicDBObject;

public interface IAbstractConverterToDBObject<Entity> {
	public BasicDBObject convertEntityToDBObject(Entity entity );
}
