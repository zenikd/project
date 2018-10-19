package org.ez.vk.vk.dao.common.entity.query.reserv;

import org.ez.vk.vk.dao.common.entity.query.QueryDTO;

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
