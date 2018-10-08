package org.ez.converter.entity.reserved;


import org.ez.converter.entity.AbstractConverterFromDBObject;
import org.ez.entity.vk.db.reserved.AbstractReservedEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservedConverterFromDBObject<Entity> extends AbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		AbstractReservedEntity reservedEntity = (AbstractReservedEntity) entity;
		reservedEntity.setDateReserved(basicDBObject.getLong("dateReserve"));
		reservedEntity.setIdReserve(basicDBObject.getString("idReserve"));
		return super.setEntity(basicDBObject, entity);		
	}
}
