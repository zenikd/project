package org.ez.vk.task.impl.point;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.EmptyRequest;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlRequestParam;
import org.ez.vk.helper.web.WebHelper;
import org.ez.vk.task.impl.RootTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class EarnPointTask extends RootTask {
	@Autowired
	WebHelper webHelper;
	
	

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(1);
		UserActor actor = listAccount.get(0).getUserActor();
		try {
			String task = likestSite.getTask("http://likest.ru/api/orders.getGroups");
			Integer groupId = Integer.parseInt(task);
			joinToGroup(actor,groupId);
		} catch (EmptyRequest e) {
			String task = likestSite.getTask("http://likest.ru/api/orders.getFriends");
			Integer friendId = Integer.parseInt(task);
			addToFriend(actor, friendId);
		}
		earn();
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

	private void addToFriend(UserActor actor, Integer friendId) throws InternalException {
		try {
			System.out.println("Start friend task");
			vk.friends().add(actor, friendId).execute();
			System.out.println("sleep");
			Thread.sleep(150000);

			vk.friends().delete(actor, friendId).execute();
			System.out.println("remove friend");
			earn();
		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}
	}
}
