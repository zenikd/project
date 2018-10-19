package org.ez.vk.entity.query.search.reserv;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.ez.vk.entity.query.BaseQuery;
import org.ez.vk.entity.query.constant.Operators;

public class ReservQuery extends BaseQuery
{
	private Long MILLISEC_IN_MIN = 60l * 1000;
	private Long reserveTime;

	protected BasicDBObject resetFileds = new BasicDBObject();

	public void setReserveTime(Integer reserveTime){
		this.reserveTime = reserveTime *MILLISEC_IN_MIN;
	}

	public Long getReserveTime()
	{
		return reserveTime;
	}

	public ReservQuery addResetFileds(String filed){
		addQueryParam(resetFileds,filed, Operators.$EQ,0);
		return this;
	}


}
