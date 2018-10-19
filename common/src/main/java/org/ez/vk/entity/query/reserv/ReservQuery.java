package org.ez.vk.entity.query.reserv;

import org.ez.vk.entity.query.QueryDTO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ReservQuery
{
	private Long MILLISEC_IN_MIN = 60l * 1000;
	private Long reserveTime;

	DBObject resetFiled = new BasicDBObject();

	public void setReserveTime(Integer reserveTime){
		this.reserveTime = reserveTime *MILLISEC_IN_MIN;
	}

	public Long getReserveTime()
	{
		return reserveTime;
	}

	public void addResetFiled(String filed){
		resetFiled.put(filed, 0);
	}

}
