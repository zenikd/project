package org.ez.vk.entity.db.factory;

import java.util.Date;

import org.ez.vk.entity.db.BaseEntity;

public  abstract class FactoryDbEntity<Entity>
{
	public void setDefaultParam(Entity entity)
	{
		BaseEntity baseEntity = (BaseEntity) entity;
		baseEntity.setUpdated(new Date().getTime());
	}

	public abstract Entity getEntity();
}
