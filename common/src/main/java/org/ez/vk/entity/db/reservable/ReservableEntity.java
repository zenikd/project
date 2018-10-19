package org.ez.vk.entity.db.reservable;

import org.ez.vk.entity.db.BaseEntity;

public abstract class ReservableEntity extends BaseEntity
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
