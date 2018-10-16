package org.ez.api.dao;

import java.util.List;

import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;

public interface IAbstractDao<Entity, SearchDTO> {
	public void addEntity(Entity entity) throws  InternalException , RootUserException;
	public void addListEntity(List<Entity> entities) throws  InternalException , RootUserException;
	public List<Entity> select(SearchDTO searchDTO) throws InternalException ;
}
