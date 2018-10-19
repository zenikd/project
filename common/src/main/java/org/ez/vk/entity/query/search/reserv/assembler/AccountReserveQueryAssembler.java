package org.ez.vk.entity.query.search.reserv.assembler;

import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.query.search.reserv.ReservQuery;

public class AccountReserveQueryAssembler implements AbstractReserveQueryAssembler
{
	public ReservQuery getReserveQuery()
	{
		ReservQuery reservQuery = new ReservQuery();
		reservQuery.addResetFileds(AccountConst.COUNT_QUERY);
		reservQuery.addResetFileds(AccountConst.COUNT_LOAD);
		reservQuery.addResetFileds(AccountConst.COUNT_COMMENT);
		return reservQuery;
	}
}
