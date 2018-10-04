package org.ez.api.converter;

import com.mongodb.BasicDBObject;

public interface IAbstractConverterToDBObject<Entity> {
	public BasicDBObject convertEntityToDBObject(Entity entity, BasicDBObject basicDBObject );
}
