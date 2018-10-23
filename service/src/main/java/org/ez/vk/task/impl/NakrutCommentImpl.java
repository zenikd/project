package org.ez.vk.task.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.SearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

@Service
public class NakrutCommentImpl extends RootTask {
	
	private final static Integer COUNT_ACCOUNT = 10;

	public void addTask(String href) throws InternalException {

	}

	public void executeTask() {
		try {
			List<AccountVk> listAccount= getListWorkAccount(COUNT_ACCOUNT);
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
			vk.wall().createComment(accountVk.getUserActor(), post.getValue()).ownerId(post.getKey())
					.message(i.toString()).execute();
			Thread.sleep(2000);
		}
	}

}
