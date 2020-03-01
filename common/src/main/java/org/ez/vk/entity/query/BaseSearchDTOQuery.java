package org.ez.vk.entity.query;

public class BaseSearchDTOQuery
{
	private DBQuery searchQuery = new DBQuery();
	private Integer limit = 1;
	

	public Integer getLimit() {
		return limit;
	}

	public BaseSearchDTOQuery setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public DBQuery getSearchQuery() { 
		return searchQuery;
	}

	public BaseSearchDTOQuery setSearchQuery(DBQuery searchQuery) {
		this.searchQuery = searchQuery;
		return this;
	}
}
