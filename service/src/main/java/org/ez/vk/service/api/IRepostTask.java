package org.ez.vk.service.api;

import org.ez.vk.dao.common.exception.internal.InternalException;

public interface IRepostTask {
	public void findPostToRepost(String groupName, int count) throws InternalException;
	public void addNewGroupToFound(String town) throws InternalException;
}
