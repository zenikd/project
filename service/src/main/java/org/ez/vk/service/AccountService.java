package org.ez.vk.service;

import org.ez.vk.entity.AccountServiceDTO;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

public interface AccountService
{
	void addAccount(AccountServiceDTO accountServiceDTO) throws RootUserException, InternalException;
}
