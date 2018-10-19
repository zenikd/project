package org.ez.vk.entity.query.search.builder;

import org.ez.vk.entity.query.search.FullSearchQuery;
import org.ez.vk.entity.query.search.SearchQuery;
import org.ez.vk.entity.query.search.reserv.assembler.AbstractReserveQueryAssembler;
import org.ez.vk.entity.query.search.reserv.assembler.EmptyReserveQueryAssembler;

public class BaseBuilder
{
	AbstractReserveQueryAssembler reserveQueryAssembler = new EmptyReserveQueryAssembler();

	public FullSearchQuery getSerchQuery()
	{
		FullSearchQuery fullSearchQuery = new FullSearchQuery();
		fullSearchQuery.setReservQuery(reserveQueryAssembler.getReserveQuery());
		return fullSearchQuery;

	}
}
