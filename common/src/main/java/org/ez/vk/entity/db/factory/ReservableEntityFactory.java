package org.ez.vk.entity.db.factory;

import java.util.Date;

import javax.swing.text.html.parser.Entity;

import org.ez.vk.entity.db.reservable.ReservableEntity;

public abstract class ReservableEntityFactory<Entity> extends FactoryDbEntity<Entity>
{
	@Override
	public void setDefaultParam(Entity entity)
	{
		ReservableEntity reservableEntity = (ReservableEntity)entity;
		reservableEntity.setDateReserved(new Date().getTime());
		super.setDefaultParam(entity);
	}
}
