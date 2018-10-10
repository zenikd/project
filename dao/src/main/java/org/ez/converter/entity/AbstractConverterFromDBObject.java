package org.ez.converter.entity;

import org.ez.api.converter.entity.IAbstractConverterFromDBObject;
import org.ez.converter.entity.constant.BaseEntityConst;
import org.ez.vk.dao.common.entity.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterFromDBObject<Entity> implements IAbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setObjectId(basicDBObject.getObjectId(BaseEntityConst._ID));
		baseEntity.setUpdate(basicDBObject.getLong(BaseEntityConst.UPDATED));
		return (Entity) baseEntity;
	}

}
