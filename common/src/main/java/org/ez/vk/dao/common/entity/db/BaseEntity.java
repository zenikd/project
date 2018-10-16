package org.ez.vk.dao.common.entity.db;

import org.bson.types.ObjectId;

public abstract class BaseEntity {
	private ObjectId objectId;
	private Long updated;
	
	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

}
