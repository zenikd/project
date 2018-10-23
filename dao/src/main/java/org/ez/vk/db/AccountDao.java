package org.ez.vk.db;

import java.util.List;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.SearchDTOQuery;
import org.ez.vk.entity.query.update.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.exception.internal.InternalException;

public interface AccountDao extends AbstractDao<AccountVk, SearchDTOQuery>
{
	public List<AccountVk> reserveAccount(ReserveAccountDTOQuery reserveDTO) throws InternalException;
}