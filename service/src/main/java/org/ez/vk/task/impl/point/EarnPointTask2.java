package org.ez.vk.task.impl.point;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.CaptchaException;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlRequestParam;
import org.ez.vk.helper.web.UrlResponseParam;
import org.ez.vk.helper.web.WebHelper;
import org.ez.vk.task.impl.RootTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class EarnPointTask2 extends RootTask {
	@Autowired
	LikestAuthorizer likestAuthorizer;
	@Autowired
	LikestTaskHelper taskHelper;
	@Autowired
	BalanceUtil balanceUtil;

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(4);
		int curAcc = 0;
		while (true) {

			AccountVk accountVk = listAccount.get(curAcc % listAccount.size());
			curAcc++;
			if (accountVk.getLikestSiteToken() == null) {
				likestAuthorizer.authorize(accountVk);
				accountDao.updateEntity(accountVk);
			}

			TaskParam taskParam = taskHelper.getOid("http://likest.ru/api/orders.getGroups", accountVk);
			String task = taskHelper.getTask(taskParam, accountVk);
			Integer groupId = Integer.parseInt(task);
			joinToGroup(accountVk, groupId);
		}

	}

	private void joinToGroup(AccountVk accountVk, Integer groupId) throws InternalException {
		try {
			System.out.println("Start task");
			Integer balance = balanceUtil.getBalance(accountVk);

			vk.groups().join(accountVk.getUserActor()).groupId(groupId).execute();
			System.out.println("sleep");

			try {
				Thread.sleep(30000);
				while (balance == balanceUtil.getBalance(accountVk)) {
					Thread.sleep(15000);
				}
			} catch (Exception e) {
				Thread.sleep(60000);
			}

			vk.groups().leave(accountVk.getUserActor(), groupId).execute();
			
			System.out.println("leave");

		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}
	}

}
