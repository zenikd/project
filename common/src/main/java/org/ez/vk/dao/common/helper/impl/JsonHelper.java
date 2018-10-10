package org.ez.vk.dao.common.helper.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import org.apache.commons.lang3.CharSetUtils;
import org.bson.json.JsonParseException;
import org.ez.vk.dao.common.entity.db.BaseEntity;

public class JsonHelper
{
	private final static ObjectMapper mapper = new ObjectMapper();

	public BasicDBObject jsonToDBObject(String jsonData)
	{   //chech Exception !!!!!!!!
		return (BasicDBObject) JSON.parse(CharSetUtils.delete(jsonData, "\t\r\n\b"));
	}

	public String entityToJson(BaseEntity entity) throws JsonProcessingException
	{
		return mapper.writeValueAsString(entity);
	}

	public List<BasicDBObject> listJsonToDBObject(List<String> listJson)
	{
		List<BasicDBObject> dbObjects = new ArrayList<BasicDBObject>();
		for (String jsonData : listJson)
		{
			dbObjects.add(jsonToDBObject(jsonData));
		}
		return dbObjects;
	}

	public List<String> listEntityToJson(List<BaseEntity> listEntity) throws JsonProcessingException{
		List<String> listJson = new ArrayList<String>();
		for (BaseEntity entity : listEntity)
		{
			listJson.add(entityToJson(entity));
		}
		return listJson;
	}


}
