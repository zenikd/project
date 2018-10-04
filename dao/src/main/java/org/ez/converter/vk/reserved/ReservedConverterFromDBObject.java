package org.ez.converter.vk.reserved;

import org.ez.converter.AbstractConverterFromDBObject;
import org.ez.entity.vk.db.reserved.AbstractReservedEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservedConverterFromDBObject<Entity> extends AbstractConverterFromDBObject<Entity> {
	public Entity convertDBObjectFromEntity(BasicDBObject basicDBObject, Entity entity) {
		AbstractReservedEntity reservedEntity = (AbstractReservedEntity) entity;
		reservedEntity.setDateReserved(basicDBObject.getLong("dateReserve"));
		reservedEntity.setIdReserve(basicDBObject.getString("idReserve"));
		reservedEntity.setUpdate(basicDBObject.getLong("update"));
		return super.convertDBObjectFromEntity(basicDBObject, entity);		
	}
}
