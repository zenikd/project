package org.ez.vk.entity.db.factory;

import org.ez.vk.entity.db.factory.ReservableEntityFactory;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.springframework.stereotype.Service;
@Service
public class AccountFactory extends ReservableEntityFactory<AccountVk>
{
	@Override
	public void setDefaultParam(AccountVk accountVk)
	{
		accountVk.setCountComment(0);
		accountVk.setCountLoad(0);
		accountVk.setCountQuery(0);
		super.setDefaultParam(accountVk);
	}

	@Override
	public AccountVk getEntity()
	{
		AccountVk accountVk = new AccountVk();
		setDefaultParam(accountVk);
		return accountVk;

	}
}
