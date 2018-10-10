package org.ez.vk.dao.common.entity.search.reserved;

import org.ez.vk.dao.common.entity.db.reserved.AccountVk;

import com.mongodb.BasicDBObject;

public class AccountSearchDTO extends ReservedSearchDTO {
	private AccountVk accountVk;

	public AccountVk getAccountVk() {
		return accountVk;
	}

	public void setAccountVk(AccountVk accountVk) {
		this.accountVk = accountVk;
	}

	@Override
	protected BasicDBObject getResetFiled() {
		// TODO Auto-generated method stub
		return null;
	}

}
