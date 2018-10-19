package org.ez.vk.db;

import java.util.List;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

public interface AbstractDao<Entity, SearchDTO> {
	public void addEntity(Entity entity) throws  InternalException , RootUserException;
	public void addListEntity(List<Entity> entities) throws  InternalException , RootUserException;
	public List<Entity> select(SearchDTO searchDTO) throws InternalException ;
}
