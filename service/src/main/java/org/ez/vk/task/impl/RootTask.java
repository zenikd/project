package org.ez.vk.task.impl;

import java.util.List;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.SearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class RootTask {
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	protected final static String WORKING = "wo";
	@Autowired
	protected AccountDao accountDao;
	
	protected List<AccountVk> getListWorkAccount(Integer count) throws InternalException {
		ReserveAccountDTOQuery reserveDTO = new ReserveAccountDTOQuery();
		reserveDTO
			.setLimit(count)
			.getSearchQuery().addSearchParam(AccountConst.TYPE, Operators.$EQ, WORKING);				
		return accountDao.select(reserveDTO);
	}
}
