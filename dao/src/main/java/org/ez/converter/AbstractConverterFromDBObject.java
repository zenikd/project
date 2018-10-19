package org.ez.converter;

import org.ez.db.api.converter.entity.IAbstractConverterFromDBObject;
import org.ez.vk.dao.common.entity.db.constant.BaseEntityConst;
import org.ez.vk.dao.common.entity.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterFromDBObject<Entity> implements IAbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setObjectId(basicDBObject.getObjectId(BaseEntityConst._ID));
		baseEntity.setUpdated(basicDBObject.getLong(BaseEntityConst.UPDATED));
		return (Entity) baseEntity;
	}

}
