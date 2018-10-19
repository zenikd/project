package org.ez.vk.vk.dao.common.entity.query.search;

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
