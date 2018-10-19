package org.ez.vk.entity.query.search;

import com.mongodb.BasicDBObject;

import org.ez.vk.entity.query.BaseQuery;

public class SearchQuery extends BaseQuery
{
	private BasicDBObject searchQuery = new BasicDBObject();

	public SearchQuery addSearchParam(String filed, String operator, String value) {
		addQueryParam(searchQuery,filed,operator,value);
		return this;
	}

	public BasicDBObject getQuery() {
		return searchQuery;
	}
}
