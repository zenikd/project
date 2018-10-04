package org.ez.vk.dao.common.api.vk.converter;

import org.ez.vk.dao.common.entity.vk.db.GroupEntity;

import com.mongodb.BasicDBObject;

public interface IGroupToDBObject   {
	public BasicDBObject convertGroupToDBObject(GroupEntity groupEntity);
}
