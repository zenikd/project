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

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(1);
		AccountVk accountVk = listAccount.get(0);
		if (accountVk.getLikestSiteToken() == null) {
			likestAuthorizer.authorize(accountVk);
			accountDao.updateEntity(accountVk);
		}

		while (true) {
			TaskParam taskParam = taskHelper.getOid("http://likest.ru/api/orders.getGroups", accountVk);
			String task = taskHelper.getTask(taskParam, accountVk);
			Integer groupId = Integer.parseInt(task);
			joinToGroup(accountVk.getUserActor(), groupId);
		}

	}

	private void joinToGroup(UserActor actor, Integer groupId) throws InternalException {
		try {
			System.out.println("Start task");
			vk.groups().join(actor).groupId(groupId).execute();
			System.out.println("sleep");
			Thread.sleep(150000);

			vk.groups().leave(actor, groupId).execute();
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
