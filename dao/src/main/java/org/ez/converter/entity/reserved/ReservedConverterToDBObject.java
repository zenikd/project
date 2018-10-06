package org.ez.converter.entity.reserved;

import org.ez.converter.entity.AbstractConverterToDBObject;
import org.ez.entity.vk.db.reserved.AbstractReservedEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservedConverterToDBObject<Entity> extends AbstractConverterToDBObject<Entity>{

	public BasicDBObject convertEntityToDBObject(Entity entity,BasicDBObject basicDBObject) {
		AbstractReservedEntity reservedEntity = (AbstractReservedEntity) entity;
		setValueIfExist(reservedEntity.getIdReserve(), "idReserve", basicDBObject);
		setValueIfExist(reservedEntity.getUpdate(), "update", basicDBObject);
		setValueIfExist(reservedEntity.getDateReserved(), "dateReserve", basicDBObject);
		return super.convertEntityToDBObject(entity, basicDBObject);
	}
}
