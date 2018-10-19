package org.ez.vk.entity.db.factory;

import org.ez.vk.entity.db.GroupEntity;

public class GroupFactory extends  FactoryDbEntity<GroupEntity>
{
	public GroupEntity getEntity()
	{
		GroupEntity entity = new GroupEntity();
		super.setDefaultParam(entity);
		return entity;
	}
}
