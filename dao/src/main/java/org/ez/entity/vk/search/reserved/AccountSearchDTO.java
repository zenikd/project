package org.ez.entity.vk.search.reserved;

import org.ez.entity.vk.db.reserved.AccountVk;

public class AccountSearchDTO extends ReservedSearchDTO {
	private AccountVk accountVk;
	private int offset = 0;
	

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public AccountVk getAccountVk() {
		return accountVk;
	}	

	public void setAccountVk(AccountVk accountVk) {
		this.accountVk = accountVk;
	}

}
