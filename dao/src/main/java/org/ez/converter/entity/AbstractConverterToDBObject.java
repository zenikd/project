package org.ez.converter.entity;

import org.ez.api.converter.entity.IAbstractConverterToDBObject;
import org.ez.converter.entity.constant.BaseEntityConst;
import org.ez.vk.dao.common.entity.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterToDBObject<Entity> implements IAbstractConverterToDBObject<Entity> {

	protected BasicDBObject setDBObject(Entity entity,BasicDBObject basicDBObject) {
		BaseEntity baseEntity = (BaseEntity) entity;
		setValueIfExist(baseEntity.getObjectId(), BaseEntityConst._ID, basicDBObject);
		setValueIfExist(baseEntity.getUpdate(), BaseEntityConst.UPDATED, basicDBObject);
		return basicDBObject;
	}

	protected void setValueIfExist(Object object, String key, BasicDBObject basicDBObject) {
		if (object != null) {
			basicDBObject.append(key, object);
		}
	}

}
