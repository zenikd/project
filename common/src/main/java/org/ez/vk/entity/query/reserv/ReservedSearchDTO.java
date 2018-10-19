package org.ez.vk.entity.query.reserv;

import org.ez.vk.entity.query.QueryDTO;

import com.mongodb.BasicDBObject;

public abstract class ReservedSearchDTO extends QueryDTO
{
	private Long reservedMinute = 60l;

	protected abstract BasicDBObject getResetFiled();

	public Long getReservedMinute() {
		return reservedMinute;
	}

	public ReservedSearchDTO setReservedMinute(Long reservedMinute) {
		this.reservedMinute = reservedMinute;
		return this;
	}

}
