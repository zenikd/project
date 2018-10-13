package org.ez.vk.dao.common.entity.search;

import org.ez.vk.dao.common.entity.search.query.SearchQuery;

public class SearchDTO {
	private SearchQuery searchQuery = new SearchQuery();
	private Integer limit = 1;
	

	public Integer getLimit() {
		return limit;
	}

	public SearchDTO setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public SearchDTO setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
		return this;
	}

	

}
