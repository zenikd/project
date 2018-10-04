package org.ez.entity.vk.db;

import org.bson.types.ObjectId;

public abstract class BaseEntity {
	private ObjectId objectId;

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

}
