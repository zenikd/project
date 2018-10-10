package org.ez.vk.dao.common.entity.search;

import org.ez.vk.dao.common.entity.search.query.SearchQuery;

public class BaseSearchDTO {
	private SearchQuery searchQuery;

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
	}

	

}
