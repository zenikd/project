package org.ez.converter.reservable;


import org.ez.converter.AbstractConverterFromDBObject;
import org.ez.vk.dao.common.entity.db.constant.ReservableConst;
import org.ez.vk.dao.common.entity.db.reservable.AbstractReservableEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservableConverterFromDBObject<Entity> extends AbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		AbstractReservableEntity reservedEntity = (AbstractReservableEntity) entity;
		reservedEntity.setDateReserved(basicDBObject.getLong(ReservableConst.DATE_RESERVED));
		reservedEntity.setIdReserve(basicDBObject.getString(ReservableConst.ID_RESRVER));
		return super.setEntity(basicDBObject, entity);		
	}
}
