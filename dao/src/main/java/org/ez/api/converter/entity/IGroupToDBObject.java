package org.ez.api.converter.entity;

import org.ez.entity.vk.db.GroupEntity;

import com.mongodb.BasicDBObject;

public interface IGroupToDBObject   {
	public BasicDBObject convertGroupToDBObject(GroupEntity groupEntity);
}
