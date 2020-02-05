package org.ez.vk.service;

import org.ez.vk.entity.AccountServiceDTO;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;

import java.util.List;

public interface AccountService
{
	void addAccount(AccountServiceDTO accountServiceDTO) throws RootUserException, InternalException;

	List<AccountVk> getAccountsByType(Integer count, String type) throws InternalException;

	List<AccountVk> getAccountsByType(Integer count) throws InternalException;
}
