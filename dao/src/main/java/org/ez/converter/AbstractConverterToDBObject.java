package org.ez.converter;

import org.ez.api.converter.IAbstractConverterToDBObject;
import org.ez.entity.vk.db.BaseEntity;

import com.mongodb.BasicDBObject;

public abstract class AbstractConverterToDBObject<Entity> implements IAbstractConverterToDBObject<Entity> {

	public BasicDBObject convertEntityToDBObject(Entity entity,BasicDBObject basicDBObject) {
		BaseEntity baseEntity = (BaseEntity) entity;
		setValueIfExist(baseEntity.getObjectId(), "_id", basicDBObject);		
		return basicDBObject;
	}

	protected void setValueIfExist(Object object, String key, BasicDBObject basicDBObject) {
		if (object != null) {
			basicDBObject.append(key, object);
		}
	}

}
