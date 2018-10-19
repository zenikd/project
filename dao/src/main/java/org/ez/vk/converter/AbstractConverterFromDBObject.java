package org.ez.vk.converter;

import org.ez.vk.entity.db.BaseEntity;
import org.ez.vk.entity.db.constant.BaseEntityConst;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterFromDBObject<Entity>  {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setObjectId(basicDBObject.getObjectId(BaseEntityConst._ID));
		baseEntity.setUpdated(basicDBObject.getLong(BaseEntityConst.UPDATED));
		return (Entity) baseEntity;
	}

}
