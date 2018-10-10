package org.ez.vk.service.task.repost;

import java.util.List;

import org.ez.api.dao.IAccountDao;
import org.ez.vk.dao.common.entity.vk.db.reserved.AccountVk;
import org.ez.vk.dao.common.entity.vk.search.reserved.AccountSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class NakrutComment {

	private final static String WORKING = "workingg";
	@Autowired
	IAccountDao accountDao;

	public void addTask(String href) {
		AccountSearchDTO accountSearchDTO = getListAccount();
		List<AccountVk> listAccount = accountDao.select(accountSearchDTO);
	
	}

	private AccountSearchDTO getListAccount() {
		AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
		AccountVk accountVk = new AccountVk();
		accountVk.setType(WORKING);
		accountSearchDTO.setAccountVk(accountVk);
		accountSearchDTO.setCount(1);
		return accountSearchDTO;
	}

}
