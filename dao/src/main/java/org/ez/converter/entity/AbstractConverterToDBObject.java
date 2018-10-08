package org.ez.converter.entity;

import org.ez.api.converter.entity.IAbstractConverterToDBObject;
import org.ez.entity.vk.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterToDBObject<Entity> implements IAbstractConverterToDBObject<Entity> {

	protected BasicDBObject setDBObject(Entity entity,BasicDBObject basicDBObject) {
		BaseEntity baseEntity = (BaseEntity) entity;
		setValueIfExist(baseEntity.getObjectId(), "_id", basicDBObject);
		setValueIfExist(baseEntity.getUpdate(), "update", basicDBObject);
		return basicDBObject;
	}

	protected void setValueIfExist(Object object, String key, BasicDBObject basicDBObject) {
		if (object != null) {
			basicDBObject.append(key, object);
		}
	}

}
