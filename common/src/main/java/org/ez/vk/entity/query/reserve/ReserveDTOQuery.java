package org.ez.vk.entity.query.reserve;

import org.ez.vk.entity.query.update.UpdateDTOQuery;

public class ReserveDTOQuery extends UpdateDTOQuery{
	private Integer reserveTimeMin = 60;

	public Integer getReserveTimeMin() {
		return reserveTimeMin;
	}

	public void setReserveTimeMin(Integer reserveTimeMin) {
		this.reserveTimeMin = reserveTimeMin;
	}

}
