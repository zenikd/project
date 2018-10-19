package org.ez.vk.task.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.search.FullSearchQuery;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
@Service
public class NakrutCommentImpl {
	private final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	private final static String WORKING = "worki";
	@Autowired
	AccountDao accountDao;

	public void addTask(String href) throws InternalException {

	}

	public void executeTask() {
		try {
			FullSearchQuery accountSearchDTO = getListAccount();
			List<AccountVk> listAccount = accountDao.select(accountSearchDTO);
			AccountVk accountVk = listAccount.get(0);

			Map<Integer, Integer> listPost = new HashMap<Integer, Integer>();
			listPost.put(-161471349, 7);
			listPost.put(-160698080, 232);

			for (Map.Entry<Integer, Integer> post : listPost.entrySet()) {

				addComment(post, accountVk);
				Thread.sleep(60000);
				addComment(post, accountVk);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addComment(Map.Entry<Integer, Integer> post, AccountVk accountVk) throws Exception {
		for (Integer i = 1; i < 8; i++) {
			vk.wall().createComment(accountVk.getUserActor(), post.getValue()).ownerId(post.getKey()).message(i.toString()).execute();
			Thread.sleep(2000);
		}
	}

	private FullSearchQuery getListAccount() {
		FullSearchQuery accountSearchDTO = new FullSearchQuery();
		accountSearchDTO.setLimit(10).getSearchQuery().addSearchParam(AccountConst.TYPE, Operators.$EQ, WORKING);

		return accountSearchDTO;
	}

}
