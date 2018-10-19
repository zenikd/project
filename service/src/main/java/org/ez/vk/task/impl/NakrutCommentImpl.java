package org.ez.vk.task.impl;

import java.util.List;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.search.FullSearchQuery;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.beans.factory.annotation.Autowired;

public class NakrutCommentImpl
{

	private final static String WORKING = "workinggg";
	@Autowired
	AccountDao accountDao;

	public void addTask(String href) throws InternalException {
		FullSearchQuery accountSearchDTO = getListAccount();
		List<AccountVk> listAccount = accountDao.select(accountSearchDTO);

	}

	private FullSearchQuery getListAccount() {
		FullSearchQuery accountSearchDTO = new FullSearchQuery();
		return accountSearchDTO;
	}

}
