package org.ez.vk.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

public interface FinderGroupByProduct
{
	void getListGroup(String groupTag) throws InternalException;
}
