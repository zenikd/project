package org.ez.vk.service.api;

import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;
import org.ez.vk.service.entity.AccountServiceDTO;

public interface IAccountService {
	public void addAccount(AccountServiceDTO accountServiceDTO) throws RootUserException, InternalException;
}
