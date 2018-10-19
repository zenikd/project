package org.ez.vk.entity.query.search.reserv.assembler;

import org.ez.vk.entity.query.search.reserv.ReservQuery;

public class EmptyReserveQueryAssembler implements AbstractReserveQueryAssembler
{
	public ReservQuery getReserveQuery()
	{
		return new ReservQuery();
	}
}
