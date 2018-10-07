package org.ez.impl.reserved;

import java.util.ArrayList;
import java.util.List;

import org.ez.api.converter.entity.IAbstractConverterToDBObject;
import org.ez.api.converter.entity.IAccountConverterToDBObject;
import org.ez.api.converter.entity.IAccountFromDBObject;
import org.ez.api.dao.IAccountDao;
import org.ez.entity.vk.db.reserved.AccountVk;
import org.ez.entity.vk.search.reserved.AccountSearchDTO;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.NotUniqueException;
import org.ez.vk.dao.common.exception.user.RootUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Repository
public class AccountDao extends ReservedDao<AccountVk, AccountSearchDTO> implements IAccountDao {
	public final static String ACCOUNT_ALREADY_EXIST = "account already exists";
	@Autowired
	IAccountConverterToDBObject accountToDBObject;
	@Autowired
	IAccountFromDBObject accountFromDBObject;

	public AccountDao() {
		MongoClient mlabClient = new MongoClient(
				new MongoClientURI("mongodb://adminn:1234567qw@ds159812.mlab.com:59812/test-db"));
		MongoDatabase database = mlabClient.getDatabase("test-db");
		this.collection = database.getCollection("account", BasicDBObject.class);
	}

	public void addEntity(AccountVk defaultAccount) throws RootUserException, InternalException {
		if (!isUnique(defaultAccount)) {
			throw new NotUniqueException(ACCOUNT_ALREADY_EXIST);
		}
		setDefaultEntityParam(defaultAccount);
		BasicDBObject basicDBObject = new BasicDBObject();
		BasicDBObject document = accountToDBObject.convertEntityToDBObject(defaultAccount);
		collection.insertOne(document);
	}

	public List<AccountVk> select(AccountSearchDTO searchDTO) {
		try {
			reserveAccount(searchDTO);
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BasicDBObject basicDBObject = accountToDBObject.convertEntityToDBObject(searchDTO.getAccountVk());

		List<BasicDBObject> listDocumnet = collection.find(basicDBObject).limit(searchDTO.getCount())
				.skip(searchDTO.getOffset()).into(new ArrayList<BasicDBObject>());
		return convertJsonToEntity(listDocumnet, accountToDBObject);

	}

	protected void setDefaultEntityParam(AccountVk accountVk) throws InternalException {
		accountVk.setCountComment(0);
		accountVk.setCountLoad(0);
		accountVk.setCountQuery(0);
		super.setDefaultEntityParam(accountVk);
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

	private BasicDBObject entityToBaseDBObject(AccountVk defaultAccount) {
		return new BasicDBObject("idReserve", "idBlock");
	}
}
