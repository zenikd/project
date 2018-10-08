package org.ez.converter.entity;

import org.ez.api.converter.entity.IAbstractConverterFromDBObject;
import org.ez.entity.vk.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterFromDBObject<Entity> implements IAbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setObjectId(basicDBObject.getObjectId("_id"));
		baseEntity.setUpdate(basicDBObject.getLong("update"));
		return (Entity) baseEntity;
	}

}
