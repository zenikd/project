package org.ez.vk.dao.common.api.vk.converter;

import com.mongodb.BasicDBObject;

public interface IAbstractConverterToDBObject<Entity> {
	public BasicDBObject convertEntityToDBObject(Entity entity, BasicDBObject basicDBObject );
}
