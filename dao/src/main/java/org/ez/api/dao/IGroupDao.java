package org.ez.api.dao;

import java.util.List;

import org.ez.entity.vk.db.GroupEntity;

public interface IGroupDao {
	public void addGroups(List<GroupEntity> groups);

}
