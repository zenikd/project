package org.ez.vk.converter;

import com.mongodb.BasicDBObject;
import org.ez.vk.entity.db.GroupEntity;
import org.ez.vk.entity.db.constant.GroupConst;
import org.springframework.stereotype.Repository;

@Repository
public class GroupFromDBObject extends AbstractConverterFromDBObject<GroupEntity> {

	@Override
	public GroupEntity setEntity(BasicDBObject basicDBObject, GroupEntity groupEntity) {
		groupEntity.setTag(basicDBObject.getString(GroupConst.TAG));
		groupEntity.setId(basicDBObject.getInt(GroupConst.ID));
		return super.setEntity(basicDBObject, groupEntity);
	}
	
	public GroupEntity  convertDBObjectFromEntity(BasicDBObject basicDBObject) {
		return setEntity(basicDBObject, new GroupEntity());
	}

}
