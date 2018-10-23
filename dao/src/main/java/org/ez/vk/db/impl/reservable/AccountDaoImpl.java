package org.ez.vk.db.impl.reservable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ez.vk.converter.reservable.AccountFromDBObject;
import org.ez.vk.db.AccountDao;
import org.ez.vk.db.impl.AbstractDao;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.NotUniqueException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Repository
public class AccountDaoImpl extends AbstractDao<AccountVk> implements AccountDao {
	public final static String ACCOUNT_ALREADY_EXIST = "account already exists";
	@Autowired
	AccountFromDBObject accountFromDBObject;
	@Autowired
	DateHelper dateHelper;

	public void addEntity(AccountVk defaultAccount) throws InternalException, RootUserException {
		if (!isUnique(defaultAccount)) {
			throw new NotUniqueException(ACCOUNT_ALREADY_EXIST);
		}
		super.addEntity(defaultAccount);
	}

	public List<AccountVk> reserveAccount(ReserveAccountDTOQuery reserveDTO) throws InternalException {
		BasicDBObject searchQuery = reserveDTO.getSearchQuery().getQuery();
		BasicDBObject updateQuery = reserveDTO.getResetFiled().getQuery();
		searchQuery.append(AccountConst.DATE_RESERVED, new BasicDBObject(Operators.$LTE, dateHelper.getEndPrivDay()));
		updateQuery.append(Operators.$SET, new BasicDBObject(AccountConst.DATE_RESERVED,
				new Date().getTime() + reserveDTO.getReserveTimeMin() * 1000000));
		reserveDTO.setUpdateQuery(reserveDTO.getResetFiled());
		Integer countReserved = updateEntity(reserveDTO);
		return select(reserveDTO);

	}

	private boolean isUnique(AccountVk defaultAccount) {
		BasicDBObject document = new BasicDBObject("type", defaultAccount.getType()).append("id",
				defaultAccount.getUserActor().getId());
		List<BasicDBObject> foundDocument = collection.find(document).limit(1).into(new ArrayList());
		if (foundDocument.size() > 0) {
			return false;
		}
		return true;

	}

	@Override
	protected List<AccountVk> convetListJsonToEntity(List<BasicDBObject> resultSearchJson) throws InternalException {
		List<AccountVk> listAccount = new ArrayList();
		for (BasicDBObject json : resultSearchJson) {
			listAccount.add(accountFromDBObject.convertDBObjectFromEntity(json));
		}
		return listAccount;
	}

	@Override
	protected AccountVk getEntityInstance() {
		return new AccountVk();
	}

	@Override
	protected void setCollection() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds159812.mlab.com:59812/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("account", BasicDBObject.class);

	}

}
