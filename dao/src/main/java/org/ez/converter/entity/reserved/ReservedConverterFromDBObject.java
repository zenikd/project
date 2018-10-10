package org.ez.converter.entity.reserved;


import org.ez.converter.entity.AbstractConverterFromDBObject;
import org.ez.converter.entity.constant.reserved.ReservedConst;
import org.ez.vk.dao.common.entity.db.reserved.AbstractReservedEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservedConverterFromDBObject<Entity> extends AbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		AbstractReservedEntity reservedEntity = (AbstractReservedEntity) entity;
		reservedEntity.setDateReserved(basicDBObject.getLong(ReservedConst.DATE_RESERVED));
		reservedEntity.setIdReserve(basicDBObject.getString(ReservedConst.ID_RESRVER));
		return super.setEntity(basicDBObject, entity);		
	}
}
