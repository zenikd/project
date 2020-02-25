package org.ez.vk.task.impl.point.task;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.TaskParam;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class AddFriendTask extends AbstractOidTask {
	private static final Logger log = Logger.getLogger(AddFriendTask.class);

	public void taskBody(AccountVk accountVk, TaskParam taskParam)
			throws ApiException, ClientException, InterruptedException, InternalException {
		log.info(accountVk.getUserName() + " start friend task");
		Integer friendId = taskHelper.getDefaultTask(taskParam, accountVk);

		vk.friends().add(accountVk.getUserActor(), friendId).execute();
		log.info("added friend and start sleep");
		Thread.sleep(120000);

		vk.friends().delete(accountVk.getUserActor(), friendId).execute();
		log.info(accountVk.getUserName() + " remove friend");

	}
}
