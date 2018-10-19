package org.ez.vk.entity.query;

import org.ez.vk.entity.query.search.SearchQuery;

public class QueryDTO
{
	private SearchQuery searchQuery = new SearchQuery();
	private Integer limit = 1;
	

	public Integer getLimit() {
		return limit;
	}

	public QueryDTO setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public QueryDTO setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
		return this;
	}

	

}
