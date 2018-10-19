package org.ez.vk.entity.query;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UpdateQuery extends  BaseQuery
{
	private BasicDBObject whereQuery = new BasicDBObject();
	private BasicDBObject updateFieldQuery = new BasicDBObject();

	public UpdateQuery addWhereParam(String filed, String operator, String value) {
		addQueryParam(whereQuery,filed,operator,value);
		return this;
	}

	public UpdateQuery addupdateFieldParam(String filed, String operator, String value) {
		addQueryParam(updateFieldQuery,filed,operator,value);
		return this;
	}

	public BasicDBObject getWhereQuery()
	{
		return whereQuery;
	}

	public BasicDBObject getUpdateFieldQuery()
	{
		return updateFieldQuery;
	}
}
