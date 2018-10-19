package org.ez.vk.entity.query.reserv.builder;

import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.query.reserv.ReservQuery;

public class AccountReserveQueryBuilder implements ReserveQueryBuilder
{

	public ReservQuery buildReserveQuery()
	{
		ReservQuery reservQuery = new ReservQuery();
		reservQuery.addResetFiled(AccountConst.COUNT_COMMENT);
		reservQuery.addResetFiled(AccountConst.COUNT_LOAD);
		reservQuery.addResetFiled(AccountConst.COUNT_QUERY);
		return  reservQuery;

	}
}
