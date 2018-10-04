package org.ez.entity.vk.search;

import org.ez.entity.vk.db.reserved.AccountVk;

public class AccountSearchDTO extends BaseSearchDTO {
	private AccountVk accountVk;
	private int offset = 0;
	private int count = 1;	

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public AccountVk getAccountVk() {
		return accountVk;
	}	

	public void setAccountVk(AccountVk accountVk) {
		this.accountVk = accountVk;
	}

}
