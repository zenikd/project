package org.ez.vk.dao.common.entity.search.query;

import com.mongodb.BasicDBObject;

public class SearchQuery {
	private BasicDBObject query = new BasicDBObject();

	public SearchQuery addSearchParam(String filed, String operator, String value) {
		BasicDBObject queryParam = new BasicDBObject(operator, value);
		query.append(filed, queryParam);
		return this;
	}

	public BasicDBObject getQuery() {
		return query;
	}
}
