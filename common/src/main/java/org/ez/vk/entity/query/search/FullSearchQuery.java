package org.ez.vk.entity.query.search;

import org.ez.vk.entity.query.search.reserv.ReservQuery;

public class FullSearchQuery
{
	private SearchQuery searchQuery = new SearchQuery();
	private ReservQuery reservQuery;
	private Integer limit = 1;
	

	public Integer getLimit() {
		return limit;
	}

	public FullSearchQuery setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public FullSearchQuery setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
		return this;
	}

	public ReservQuery getReservQuery()
	{
		return reservQuery;
	}

	public void setReservQuery(ReservQuery reservQuery)
	{
		this.reservQuery = reservQuery;
	}
}
