package org.ez.vk.task.impl;

import java.util.List;

import org.ez.db.api.dao.IAccountDao;
import org.ez.vk.dao.common.entity.db.reservable.AccountVk;
import org.ez.vk.dao.common.entity.query.reserv.AccountSearchDTO;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.springframework.beans.factory.annotation.Autowired;

public class NakrutCommentImpl
{

	private final static String WORKING = "workinggg";
	@Autowired
	IAccountDao accountDao;

	public void addTask(String href) throws InternalException {
		AccountSearchDTO accountSearchDTO = getListAccount();
		List<AccountVk> listAccount = accountDao.select(accountSearchDTO);

	}

	private AccountSearchDTO getListAccount() {
		AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
		return accountSearchDTO;
	}

}
