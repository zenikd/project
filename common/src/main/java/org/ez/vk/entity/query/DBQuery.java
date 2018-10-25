package org.ez.vk.entity.query;

import com.mongodb.BasicDBObject;

public class DBQuery
{
	private BasicDBObject searchQuery = new BasicDBObject();
	
	public DBQuery addQueryParam(String filed, String operator, Object value) {
		BasicDBObject queryParam = new BasicDBObject(operator, value);
		searchQuery.append(filed, queryParam);
		return this;
	}

	public BasicDBObject getQuery() {
		return searchQuery;
	}
}
