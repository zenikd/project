package org.ez.converter.entity.reserved;

import org.ez.api.converter.entity.IAccountConverterToDBObject;
import org.ez.converter.entity.constant.reserved.AccountConst;
import org.ez.vk.dao.common.entity.db.reserved.AccountVk;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository
public class AccountToDBObject extends ReservedConverterToDBObject<AccountVk> implements IAccountConverterToDBObject {

	protected BasicDBObject setDBObject(AccountVk accountVk,BasicDBObject basicDBObject)  {
		if (accountVk.getUserActor() != null) {
			setValueIfExist(accountVk.getUserActor().getId(), AccountConst.ID, basicDBObject);
			setValueIfExist(accountVk.getUserActor().getAccessToken(), AccountConst.TOKEN, basicDBObject);
		}	
		setValueIfExist(accountVk.getType(), AccountConst.TYPE, basicDBObject);
		setValueIfExist(accountVk.getCustomAccountUrl(), AccountConst.CUSTOM_ACCOUNT_URL, basicDBObject);
		setValueIfExist(accountVk.getDefaultAccountUrl(), AccountConst.DEFAULT_ACCOUNT_URL, basicDBObject);
		setValueIfExist(accountVk.getUserLogin(), AccountConst.USER_LOGIN, basicDBObject);
		setValueIfExist(accountVk.getUserPass(), AccountConst.USER_PASS, basicDBObject);
		setValueIfExist(accountVk.getUserName(), AccountConst.USER_NAME, basicDBObject);
		setValueIfExist(accountVk.getCountComment(), AccountConst.COUNT_COMMENT, basicDBObject);
		setValueIfExist(accountVk.getCountQuery(), AccountConst.COUNT_QUERY, basicDBObject);
		setValueIfExist(accountVk.getCountLoad(), AccountConst.COUNT_LOAD, basicDBObject);
		return super.setDBObject(accountVk, basicDBObject);
	}
	
	public BasicDBObject convertEntityToDBObject(AccountVk accountVk) {
		BasicDBObject basicDBObject = new BasicDBObject();
		return setDBObject(accountVk, basicDBObject);
	}
	

}
