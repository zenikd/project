package org.ez.vk.db.impl.reservable;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import org.ez.db.api.converter.entity.IAccountFromDBObject;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.NotUniqueException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.db.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends ReservableDao<AccountVk> implements AccountDao
{
	public final static String ACCOUNT_ALREADY_EXIST = "account already exists";
	@Autowired
	IAccountFromDBObject accountFromDBObject;

	public AccountDaoImpl() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds159812.mlab.com:59812/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("account", BasicDBObject.class);
	}

	public void addEntity(AccountVk defaultAccount) throws  InternalException, RootUserException
	{
		if (!isUnique(defaultAccount)) {
			throw new NotUniqueException(ACCOUNT_ALREADY_EXIST);
		}
		super.addEntity(defaultAccount);
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
	protected List<AccountVk> convetJsonToEntity(List<BasicDBObject> resultSearchJson) throws InternalException {
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

}
