package org.ez.vk.entity.db;

public class GroupEntity extends BaseEntity {
	private Integer id; 
	private String tag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
