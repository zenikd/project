package org.ez.converter.entity;

import org.ez.api.converter.entity.IGroupToDBObject;
import org.ez.converter.entity.constant.GroupConst;
import org.ez.entity.vk.db.GroupEntity;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
@Service
public class GroupToDBObject extends AbstractConverterToDBObject<GroupEntity> implements IGroupToDBObject{
	public BasicDBObject setDBObject(GroupEntity groupEntity, BasicDBObject basicDBObject) {
		setValueIfExist(groupEntity.getTown(), GroupConst.TOWN, basicDBObject);
		setValueIfExist(groupEntity.getType(), GroupConst.TYPE, basicDBObject);
		return super.setDBObject(groupEntity,basicDBObject);
	}
	
	public BasicDBObject convertEntityToDBObject(GroupEntity groupEntity) {
		BasicDBObject basicDBObject = new BasicDBObject();
		return setDBObject(groupEntity, basicDBObject);
	}


}
