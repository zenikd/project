package org.ez.impl.reserved;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ez.vk.dao.common.entity.db.reservable.AbstractReservableEntity;
import org.ez.vk.dao.common.entity.search.reserved.ReservedSearchDTO;
import org.ez.impl.AbstractDao;
import org.ez.vk.dao.common.exception.internal.InternalException;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

public abstract class ReservedDao<Entity> extends AbstractDao<Entity> {


	private static final String DEFAULT_RESERVE = "defaultReserve";
	private static final Long MILSEC_IN_MINUTE = 600000l;
	private static final Long MILSEC_IN_QUERY = 20000l;

	protected void reserveAccount(ReservedSearchDTO searchDTO) throws InternalException {
		List<BasicDBObject> listDBObjects = new ArrayList<BasicDBObject>();
		//resevedNotUsedEntity(searchDTO);
	}

/*	protected Integer resevedNotUsedEntity(ReservedSearchDTO searchDTO) throws InternalException {
		BasicDBObject queryDoc = searchDTO.getSearchQuery().getQuery();
		BasicDBObject queryDocParam = new BasicDBObject("$lte", getEndPrivDay());
		queryDoc.append("dateReserve", queryDocParam);
		BasicDBObject updateDoc = searchDTO.getSearchQuery().getQuery();

		reserveQuery(searchDTO, queryDoc, updateDoc);
	}

	private void reserveQuery(ReservedSearchDTO searchDTO, BasicDBObject queryDoc, BasicDBObject updateDoc) {
		updateDoc.append("$set", updateTimeReserved);
		Integer countEntity = searchDTO.getCount();
		for (int curEntity = 0; curEntity < countEntity; curEntity++) {
			updateTimeReserved.append("dateReserve", new Date().getTime()
					+ searchDTO.getReservedMinute() * MILSEC_IN_MINUTE + MILSEC_IN_QUERY * (countEntity - curEntity));
			UpdateResult listDocumnet = collection.updateOne(queryDoc, updateDoc);
		}
	}*/

	

	protected void setDefaultEntityParam(Entity entity) throws InternalException {
		AbstractReservableEntity reservedEntity = (AbstractReservableEntity) entity;
		reservedEntity.setDateReserved(getEndPrivDay());
		reservedEntity.setIdReserve(DEFAULT_RESERVE);
	}

}
