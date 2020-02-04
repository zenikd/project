package org.ez.vk.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

public interface RepostTask
{
	void findPostToRepost(String groupName, int count) throws InternalException;
	void addNewGroupToFound(String town) throws InternalException, RootUserException;
}
