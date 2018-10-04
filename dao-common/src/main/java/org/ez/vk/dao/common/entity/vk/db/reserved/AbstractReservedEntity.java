package org.ez.vk.dao.common.entity.vk.db.reserved;

import org.ez.vk.dao.common.entity.vk.db.BaseEntity;

public abstract class AbstractReservedEntity extends BaseEntity{
	private String idReserve;
	private Long dateReserved;
	private Long update;
	public String getIdReserve() {
		return idReserve;
	}
	public void setIdReserve(String idReserve) {
		this.idReserve = idReserve;
	}
	public Long getDateReserved() {
		return dateReserved;
	}
	public void setDateReserved(Long dateReserved) {
		this.dateReserved = dateReserved;
	}
	public Long getUpdate() {
		return update;
	}
	public void setUpdate(Long update) {
		this.update = update;
	}
	
	
}
