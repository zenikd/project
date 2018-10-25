package org.ez.vk.converter.reservable;

import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.vk.api.sdk.client.actors.UserActor;

@Repository
public class AccountFromDBObject extends ReservableConverterFromDBObject<AccountVk>  {

	@Override
	public AccountVk setEntity(BasicDBObject basicDBObject, AccountVk accountVk) {
		accountVk.setType(basicDBObject.getString(AccountConst.TYPE));
		BasicDBObject userActorJson = (BasicDBObject) basicDBObject.get(AccountConst.USER_ACTOR);
		accountVk.setUserActor(new UserActor(userActorJson.getInt(AccountConst.ID), userActorJson.getString(AccountConst.TOKEN)));
		accountVk.setCustomAccountUrl(basicDBObject.getString(AccountConst.CUSTOM_ACCOUNT_URL));
		accountVk.setDefaultAccountUrl(basicDBObject.getString(AccountConst.DEFAULT_ACCOUNT_URL));
		accountVk.setUserLogin(basicDBObject.getString(AccountConst.USER_LOGIN));
		accountVk.setUserPass(basicDBObject.getString(AccountConst.USER_PASS));
		accountVk.setUserName(basicDBObject.getString(AccountConst.USER_NAME));
		accountVk.setCountComment(basicDBObject.getInt(AccountConst.COUNT_COMMENT));
		accountVk.setCountLoad(basicDBObject.getInt(AccountConst.COUNT_LOAD));
		accountVk.setCountQuery(basicDBObject.getInt(AccountConst.COUNT_QUERY));
		accountVk.setLikestSiteToken(basicDBObject.getString(AccountConst.LIKEST_TOKEN));
		accountVk.setLikestSiteCookie(basicDBObject.getString(AccountConst.LIKEST_COOKIE));
		return super.setEntity(basicDBObject, accountVk);
	}
	
	public AccountVk  convertDBObjectFromEntity(BasicDBObject basicDBObject) {
		return setEntity(basicDBObject, new AccountVk());
	}

}
