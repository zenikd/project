package org.ez.converter.entity.reserved;

import org.ez.api.converter.entity.IAccountConverterToDBObject;
import org.ez.entity.vk.db.reserved.AccountVk;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository
public class AccountToDBObject extends ReservedConverterToDBObject<AccountVk> implements IAccountConverterToDBObject {

	protected BasicDBObject setDBObject(AccountVk accountVk,BasicDBObject basicDBObject)  {
		if (accountVk.getUserActor() != null) {
			setValueIfExist(accountVk.getUserActor().getId(), "id", basicDBObject);
			setValueIfExist(accountVk.getUserActor().getAccessToken(), "token", basicDBObject);
		}	
		setValueIfExist(accountVk.getType(), "type", basicDBObject);
		setValueIfExist(accountVk.getCustomAccountUrl(), "customAccountUrl", basicDBObject);
		setValueIfExist(accountVk.getDefaultAccountUrl(), "defaultAccountUrl", basicDBObject);
		setValueIfExist(accountVk.getUserLogin(), "userLogin", basicDBObject);
		setValueIfExist(accountVk.getUserPass(), "userPass", basicDBObject);
		setValueIfExist(accountVk.getUserName(), "userName", basicDBObject);
		setValueIfExist(accountVk.getCountComment(), "countComment", basicDBObject);
		setValueIfExist(accountVk.getCountQuery(), "countQuery", basicDBObject);
		setValueIfExist(accountVk.getCountLoad(), "countLoad", basicDBObject);
		return super.setDBObject(accountVk, basicDBObject);
	}
	
	public BasicDBObject convertEntityToDBObject(AccountVk accountVk) {
		BasicDBObject basicDBObject = new BasicDBObject();
		return setDBObject(accountVk, basicDBObject);
	}
	

}
