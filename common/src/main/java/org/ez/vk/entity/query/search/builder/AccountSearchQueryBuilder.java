package org.ez.vk.entity.query.search.builder;

import org.ez.vk.entity.query.search.FullSearchQuery;
import org.ez.vk.entity.query.search.SearchQuery;
import org.ez.vk.entity.query.search.reserv.assembler.AccountReserveQueryAssembler;

public class AccountSearchQueryBuilder extends BaseBuilder
{
	AccountSearchQueryBuilder(){
		super.reserveQueryAssembler = new AccountReserveQueryAssembler();
	}
}
