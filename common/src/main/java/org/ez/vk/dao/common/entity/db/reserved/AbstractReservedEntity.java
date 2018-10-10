package org.ez.vk.dao.common.entity.db.reserved;

import org.ez.vk.dao.common.entity.db.BaseEntity;

public abstract class AbstractReservedEntity extends BaseEntity
{
	private String idReserve;
	private Long dateReserved;

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
	
	
}
