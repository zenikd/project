package org.ez.entity.vk.search.query;

import com.mongodb.BasicDBObject;

public class SearchQuery {
	private BasicDBObject query = new BasicDBObject();

	public void addSearchParam(String filed, String operator, String value) {
		BasicDBObject queryParam = new BasicDBObject(operator, value);
		query.append(filed, queryParam);
	}

	public BasicDBObject getQuery() {
		return query;
	}
}
