package org.ez.vk.service;

import org.ez.vk.entity.AccountServiceDTO;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

import java.io.IOException;
import java.util.List;

public interface GroupService
{
	void addGroupsFromFile(String tag) throws IOException;
}
