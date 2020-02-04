package org.ez.vk.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

public interface FinderPopularPost
{
	void getListPost(String groupTag) throws InternalException;
}
