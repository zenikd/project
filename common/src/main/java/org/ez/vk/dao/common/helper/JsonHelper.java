package org.ez.vk.dao.common.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.CharSetUtils;
import org.ez.vk.dao.common.entity.db.BaseEntity;
import org.ez.vk.dao.common.exception.internal.InternalException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class JsonHelper<Entity> {
	private final static ObjectMapper mapper = new ObjectMapper();

	private final static String PARSE_EXCEPTION = "parse exception";
	private final static String JSON_MAPPING_EXCEPTION = "json mapping exception";
	private final static String IOEXCEPTION = "IOEx";

	public BasicDBObject jsonToDBObject(String jsonData) { // chech Exception !!!!!!!!
		return (BasicDBObject) JSON.parse(CharSetUtils.delete(jsonData, "\t\r\n\b"));
	}

	public String entityToJson(BaseEntity entity) throws InternalException {
		try {
			return mapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			throw new InternalException();
		}
	}

	public List<BasicDBObject> listJsonToDBObject(List<String> listJson) {
		List<BasicDBObject> dbObjects = new ArrayList<BasicDBObject>();
		for (String jsonData : listJson) {
			dbObjects.add(jsonToDBObject(jsonData));
		}
		return dbObjects;
	}

	public BasicDBObject entityToDBObject(BaseEntity entity) throws InternalException {
		return jsonToDBObject(entityToJson(entity));
	}

	public List<String> listEntityToJson(List<BaseEntity> listEntity) throws InternalException {
		List<String> listJson = new ArrayList<String>();
		for (BaseEntity entity : listEntity) {
			listJson.add(entityToJson(entity));
		}
		return listJson;
	}

	public Entity bsonToEntity(BasicDBObject basicDBObject, Entity entity) throws InternalException {
		try {
			return (Entity) mapper.readValue(basicDBObject.toJson(), entity.getClass());
		} catch (com.fasterxml.jackson.core.JsonParseException e) {
			throw new InternalException();
		} catch (JsonMappingException e) {
			throw new InternalException();
		} catch (IOException e) {
			throw new InternalException();
		}
	}

}
