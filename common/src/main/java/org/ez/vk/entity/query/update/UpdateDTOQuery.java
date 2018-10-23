package org.ez.vk.entity.query.update;

import org.ez.vk.entity.query.DBQuery;
import org.ez.vk.entity.query.SearchDTOQuery;

import com.mongodb.BasicDBObject;

public class UpdateDTOQuery extends SearchDTOQuery{
	private DBQuery updateQuery = new DBQuery();

	public DBQuery getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(DBQuery updateQuery) {
		this.updateQuery = updateQuery;
	}
	
}
