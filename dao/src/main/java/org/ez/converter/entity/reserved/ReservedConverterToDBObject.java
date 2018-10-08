package org.ez.converter.entity.reserved;

import org.ez.converter.entity.AbstractConverterToDBObject;
import org.ez.converter.entity.constant.reserved.ReservedConst;
import org.ez.entity.vk.db.reserved.AbstractReservedEntity;

import com.mongodb.BasicDBObject;

public abstract class ReservedConverterToDBObject<Entity> extends AbstractConverterToDBObject<Entity>{

	protected BasicDBObject setDBObject(Entity entity,BasicDBObject basicDBObject) {
		AbstractReservedEntity reservedEntity = (AbstractReservedEntity) entity;
		setValueIfExist(reservedEntity.getIdReserve(), ReservedConst.ID_RESRVER, basicDBObject);
		setValueIfExist(reservedEntity.getDateReserved(), ReservedConst.DATE_RESERVED, basicDBObject);
		return super.setDBObject(entity, basicDBObject);
	}
}
