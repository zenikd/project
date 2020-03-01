package org.ez.vk.db;

import java.util.List;

import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.entity.query.BaseSearchDTOQuery;
import org.ez.vk.entity.query.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.exception.internal.InternalException;

public interface AccountDao extends AbstractDao<AccountVk, BaseSearchDTOQuery>
{
	List<AccountVk> reserveAccount(ReserveAccountDTOQuery reserveDTO) throws InternalException;
	void removeAccount(AccountVk accountVk) throws InternalException;
}