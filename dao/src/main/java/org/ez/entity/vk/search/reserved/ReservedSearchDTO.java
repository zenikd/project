package org.ez.entity.vk.search.reserved;

import org.ez.entity.vk.search.BaseSearchDTO;

import com.mongodb.BasicDBObject;

public class ReservedSearchDTO extends BaseSearchDTO {
	private Long reservedMinute;
	private boolean reserve;
	private BasicDBObject queryDoc = new BasicDBObject();
	private BasicDBObject updateDoc = new BasicDBObject();
	private int count = 1;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BasicDBObject getQueryDoc() {
		return queryDoc;
	}

	public void setQueryDoc(BasicDBObject queryDoc) {
		this.queryDoc = queryDoc;
	}

	public BasicDBObject getUpdateDoc() {
		return updateDoc;
	}

	public void setUpdateDoc(BasicDBObject updateDoc) {
		this.updateDoc = updateDoc;
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
