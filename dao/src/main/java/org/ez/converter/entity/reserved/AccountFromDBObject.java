package org.ez.converter.entity.reserved;

import org.ez.api.converter.entity.IAccountFromDBObject;
import org.ez.entity.vk.db.reserved.AccountVk;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.vk.api.sdk.client.actors.UserActor;

@Repository
public class AccountFromDBObject extends ReservedConverterFromDBObject<AccountVk> implements IAccountFromDBObject {

	@Override
	public AccountVk setEntity(BasicDBObject basicDBObject, AccountVk accountVk) {
		accountVk.setType(basicDBObject.getString("type"));
		accountVk.setUserActor(new UserActor(basicDBObject.getInt("id"), basicDBObject.getString("token")));
		accountVk.setCustomAccountUrl(basicDBObject.getString("customAccountUrl"));
		accountVk.setDefaultAccountUrl(basicDBObject.getString("defaultAccountUrl"));
		accountVk.setUserLogin(basicDBObject.getString("userLogin"));
		accountVk.setUserPass(basicDBObject.getString("userPass"));
		accountVk.setUserName(basicDBObject.getString("userName"));
		accountVk.setCountComment(basicDBObject.getInt("countComment"));
		accountVk.setCountLoad(basicDBObject.getInt("countLoad"));
		accountVk.setCountQuery(basicDBObject.getInt("countQuery"));
		return super.setEntity(basicDBObject, accountVk);
	}
	
	public AccountVk  convertDBObjectFromEntity(BasicDBObject basicDBObject) {
		return setEntity(basicDBObject, new AccountVk());
	}

}
