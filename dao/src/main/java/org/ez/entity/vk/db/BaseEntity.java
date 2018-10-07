package org.ez.entity.vk.db;

import org.bson.types.ObjectId;

public abstract class BaseEntity {
	private ObjectId objectId;
	private Long update;
	public Long getUpdate() {
		return update;
	}

	public void setUpdate(Long update) {
		this.update = update;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

}
