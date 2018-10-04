package org.ez.converter.vk.reserved;

import org.ez.api.converter.IAccountConverterToDBObject;
import org.ez.entity.vk.db.reserved.AccountVk;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository
public class AccountToDBObject extends ReservedConverterToDBObject<AccountVk> implements IAccountConverterToDBObject {

	public BasicDBObject convertEntityToDBObject(AccountVk accountVk,BasicDBObject basicDBObject)  {
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
		return super.convertEntityToDBObject(accountVk, basicDBObject);
	}

}
