package org.ez.vk.dao.common.api.vk.converter;

import com.mongodb.BasicDBObject;

public interface IAbstractConverterFromDBObject<Entity> {
	public Entity convertDBObjectFromEntity(BasicDBObject basicDBObject,  Entity entity);
}
