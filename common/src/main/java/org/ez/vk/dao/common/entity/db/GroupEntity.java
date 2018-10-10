package org.ez.vk.dao.common.entity.db;

import org.bson.types.ObjectId;

public class GroupEntity {
	private ObjectId objectId;
	private Integer id; 
	private String town;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	

}
