package org.ez.entity.vk.db.reserved;

import org.ez.entity.vk.db.BaseEntity;

public abstract class AbstractReservedEntity extends BaseEntity{
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
