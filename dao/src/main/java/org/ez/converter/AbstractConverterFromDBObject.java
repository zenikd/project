package org.ez.converter;

import org.ez.api.converter.IAbstractConverterFromDBObject;
import org.ez.entity.vk.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterFromDBObject<Entity> implements IAbstractConverterFromDBObject<Entity> {
	public Entity convertDBObjectFromEntity(BasicDBObject basicDBObject, Entity entity) {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setObjectId(basicDBObject.getObjectId("_id"));
		return (Entity) baseEntity;
	}

}
