package org.ez.impl.reserved;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ez.entity.vk.db.reserved.AbstractReservedEntity;
import org.ez.entity.vk.search.reserved.ReservedSearchDTO;
import org.ez.impl.AbstractDao;
import org.ez.vk.dao.common.exception.internal.InternalException;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

public abstract class ReservedDao<Entity, SearchDTO> extends AbstractDao<Entity, SearchDTO> {

	private static final String DEFAULT_RESERVE = "defaultReserve";
	private static final Long MILSEC_IN_MINUTE = 600000l;

	protected void reserveAccount(ReservedSearchDTO searchDTO) throws InternalException {
		List<BasicDBObject> listDBObjects = new ArrayList<BasicDBObject>();
		resevedNotUsedEntity(searchDTO);
	}

	protected void resevedNotUsedEntity(ReservedSearchDTO searchDTO) throws InternalException {
		BasicDBObject queryDocParam = new BasicDBObject();
		queryDocParam.append("$lte", getEndPrivDay());
		BasicDBObject queryDoc = searchDTO.getQueryDoc();
		queryDoc.append("dateReserve", getEndPrivDay());

		BasicDBObject updateDocParam = new BasicDBObject();
		updateDocParam.append("dateReserve", new Date().getTime() + searchDTO.getReservedMinute() * MILSEC_IN_MINUTE);
		BasicDBObject updateDoc = searchDTO.getUpdateDoc();
		updateDoc.append("$set", updateDocParam);
		UpdateResult listDocumnet = collection.updateOne(queryDoc, updateDoc);
	}

	protected void setDefaultEntityParam(Entity entity) throws InternalException {
		AbstractReservedEntity reservedEntity = (AbstractReservedEntity) entity;
		reservedEntity.setDateReserved(getEndPrivDay());
		reservedEntity.setIdReserve(DEFAULT_RESERVE);
	}

}
