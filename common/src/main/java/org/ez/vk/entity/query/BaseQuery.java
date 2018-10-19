package org.ez.vk.entity.query;

import com.mongodb.BasicDBObject;

import org.ez.vk.entity.query.search.SearchQuery;

public class BaseQuery
{
	public void addQueryParam(BasicDBObject query, String filed, String operator, Object value) {
		BasicDBObject queryParam = new BasicDBObject(operator, value);
		query.append(filed, queryParam);
	}
}
