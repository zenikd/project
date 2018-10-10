package org.ez.entity.vk.search.reserved;

import org.ez.entity.vk.db.reserved.AccountVk;

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
