package org.ez.vk.dao.common.entity.search.query;

import java.util.Map;

public class UpdateQuery {
	private Map<String, Integer> resetFields;

	public void addResetField(String filed) {
		resetFields.put(filed, 0);
	}
	
	public void addCountLoad(String filed, Integer countLoad) {
		resetFields.put(filed, countLoad);
	}
	
	
	
}