package org.ez.vk.db;

import java.util.List;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

public interface AbstractDao<Entity, SearchDTO> {
	void addEntity(Entity entity) throws  InternalException , RootUserException;
	void addListEntity(List<Entity> entities) throws  InternalException , RootUserException;
	List<Entity> select(SearchDTO searchDTO) throws InternalException ;
	void updateEntity(Entity entity) throws InternalException;
}
