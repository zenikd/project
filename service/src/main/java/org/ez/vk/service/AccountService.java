package org.ez.vk.service;

import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;
import org.ez.vk.entity.AccountServiceDTO;

public interface AccountService
{
	public void addAccount(AccountServiceDTO accountServiceDTO) throws RootUserException, InternalException;
}
