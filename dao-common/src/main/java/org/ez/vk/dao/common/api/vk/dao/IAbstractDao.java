package org.ez.vk.dao.common.api.vk.dao;

import java.util.List;

public interface IAbstractDao<Entity, SearchDTO> {
	public void addAccount(Entity entity) throws RuntimeException;
	public List<Entity> select(SearchDTO searchDTO);
}
