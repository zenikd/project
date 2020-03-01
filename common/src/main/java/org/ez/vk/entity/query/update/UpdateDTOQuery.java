package org.ez.vk.entity.query.update;

import org.ez.vk.entity.query.DBQuery;
import org.ez.vk.entity.query.BaseSearchDTOQuery;

public class UpdateDTOQuery extends BaseSearchDTOQuery {
	private DBQuery updateQuery = new DBQuery();

	public DBQuery getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(DBQuery updateQuery) {
		this.updateQuery = updateQuery;
	}
	
}
