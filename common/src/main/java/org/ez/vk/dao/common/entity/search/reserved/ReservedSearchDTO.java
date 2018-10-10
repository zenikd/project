package org.ez.vk.dao.common.entity.search.reserved;

import org.ez.vk.dao.common.entity.search.BaseSearchDTO;

import com.mongodb.BasicDBObject;

public abstract class ReservedSearchDTO extends BaseSearchDTO
{
	private Long reservedMinute;
	private boolean reserve;
	private int count = 1;

	protected abstract BasicDBObject getResetFiled();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isReserve() {
		return reserve;
	}

	public void setReserve(boolean reserve) {
		this.reserve = reserve;
	}

	public Long getReservedMinute() {
		return reservedMinute;
	}

	public void setReservedMinute(Long reservedMinute) {
		this.reservedMinute = reservedMinute;
	}

}
