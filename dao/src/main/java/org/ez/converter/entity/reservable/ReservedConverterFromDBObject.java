package org.ez.converter.entity.reservable;


import org.ez.converter.entity.AbstractConverterFromDBObject;
import org.ez.vk.dao.common.constant.db.filed.reservable.ReservableConst;
import org.ez.vk.dao.common.entity.db.reservable.AbstractReservableEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservedConverterFromDBObject<Entity> extends AbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		AbstractReservableEntity reservedEntity = (AbstractReservableEntity) entity;
		reservedEntity.setDateReserved(basicDBObject.getLong(ReservableConst.DATE_RESERVED));
		reservedEntity.setIdReserve(basicDBObject.getString(ReservableConst.ID_RESRVER));
		return super.setEntity(basicDBObject, entity);		
	}
}
