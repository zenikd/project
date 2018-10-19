package org.ez.vk.converter.reservable;


import org.ez.vk.converter.AbstractConverterFromDBObject;
import org.ez.vk.entity.db.constant.ReservableConst;
import org.ez.vk.entity.db.reservable.ReservableEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservableConverterFromDBObject<Entity> extends AbstractConverterFromDBObject<Entity> {
	public Entity setEntity(BasicDBObject basicDBObject, Entity entity) {
		ReservableEntity reservedEntity = (ReservableEntity) entity;
		reservedEntity.setDateReserved(basicDBObject.getLong(ReservableConst.DATE_RESERVED));
		reservedEntity.setIdReserve(basicDBObject.getString(ReservableConst.ID_RESRVER));
		return super.setEntity(basicDBObject, entity);		
	}
}
