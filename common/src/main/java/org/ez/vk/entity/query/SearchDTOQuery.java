package org.ez.vk.entity.query;

import com.mongodb.BasicDBObject;

public class SearchDTOQuery 
{
	private DBQuery searchQuery = new DBQuery();
	private Integer limit = 1;
	

	public Integer getLimit() {
		return limit;
	}

	public SearchDTOQuery setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public DBQuery getSearchQuery() { 
		return searchQuery;
	}

	public SearchDTOQuery setSearchQuery(DBQuery searchQuery) {
		this.searchQuery = searchQuery;
		return this;
	}
}
