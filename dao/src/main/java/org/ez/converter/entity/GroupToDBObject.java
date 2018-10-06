package org.ez.converter.entity;

import org.ez.api.converter.entity.IGroupToDBObject;
import org.ez.entity.vk.db.GroupEntity;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
@Service
public class GroupToDBObject implements IGroupToDBObject{
	public BasicDBObject convertGroupToDBObject(GroupEntity groupEntity) {
		BasicDBObject basicDBObject = new BasicDBObject();
		setValueIfExist(groupEntity.getTown(), "town", basicDBObject);
		setValueIfExist(groupEntity.getId(), "id", basicDBObject);
		setValueIfExist(groupEntity.getType(), "type", basicDBObject);
		setValueIfExist(groupEntity.getObjectId(), "_id", basicDBObject);
		return basicDBObject;
	}
	
	protected void setValueIfExist(Object object, String key, BasicDBObject basicDBObject) {
		if (object != null) {
			basicDBObject.append(key, object);
		}
	}

}
