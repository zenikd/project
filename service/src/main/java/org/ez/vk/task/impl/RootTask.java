package org.ez.vk.task.impl;

import java.util.List;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.SearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class RootTask {
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	@Autowired
	protected AccountDao accountDao;
	
	protected List<AccountVk> getListWorkAccount(Integer count, String type) throws InternalException {
		ReserveAccountDTOQuery reserveDTO = new ReserveAccountDTOQuery();
		reserveDTO
			.setLimit(count)
			.getSearchQuery().addQueryParam(AccountConst.TYPE, Operators.$EQ, type);				
		return accountDao.select(reserveDTO);
	}
	
	protected List<AccountVk> getListWorkAccount(Integer count) throws InternalException {
		return this.getListWorkAccount(count, UserTypeEnum.WORKING.toString());
	}
	
	protected void validateAccounts(String type) throws InternalException {
		List<AccountVk> listAccount = this.getListWorkAccount(999, type);
		int counter = 0;
		for (AccountVk account : listAccount) {
			try {
				vk.account().getProfileInfo(account.getUserActor()).execute();
			} catch (Exception e1) {
				this.accountDao.removeAccount(account);
				counter++;
			}
		}
		
		if (counter > 0) System.out.println("Acoounts " + counter + " has been deleted");
	}
}
