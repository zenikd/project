package org.ez.vk.dao.common.entity.search.reserved;

import org.ez.vk.dao.common.entity.search.SearchDTO;

import com.mongodb.BasicDBObject;

public abstract class ReservedSearchDTO extends SearchDTO
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
