package org.ez.vk.task.impl.point.task;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.TaskParam;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class AddFriendTask extends AbstractOidTask {

	public void taskBody(AccountVk accountVk, TaskParam taskParam)
			throws ApiException, ClientException, InterruptedException, InternalException {

		Thread.sleep(5000);
		Integer friendId = taskHelper.getDefaultTask(taskParam, accountVk);
		System.out.println(accountVk.getUserName() + " start friend task");
		vk.friends().add(accountVk.getUserActor(), friendId).execute();
		System.out.println("sleep");
		Thread.sleep(120000);

		vk.friends().delete(accountVk.getUserActor(), friendId).execute();
		System.out.println(accountVk.getUserName() + " remove friend");

	}
}
