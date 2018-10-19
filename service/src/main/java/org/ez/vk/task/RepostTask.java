package org.ez.vk.task;

import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;

public interface RepostTask
{
	public void findPostToRepost(String groupName, int count) throws InternalException;
	public void addNewGroupToFound(String town) throws InternalException, RootUserException;
}
