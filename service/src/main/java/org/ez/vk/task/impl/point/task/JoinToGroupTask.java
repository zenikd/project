package org.ez.vk.task.impl.point.task;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.TaskParam;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class JoinToGroupTask extends AbstractOidTask {

	private static final Logger log = Logger.getLogger(JoinToGroupTask.class);

	protected void taskBody(AccountVk accountVk, TaskParam taskParam)
			throws ApiException, ClientException, InterruptedException, InternalException {
		log.info(accountVk.getUserName() + " start join to group task");
		Integer groupId = taskHelper.getDefaultTask(taskParam, accountVk);

		vk.groups().join(accountVk.getUserActor()).groupId(groupId).execute();
		log.info(accountVk.getUserName() + " joined to group and start sleep");

		Thread.sleep(120000);
		vk.groups().leave(accountVk.getUserActor(), groupId).execute();
		log.info(accountVk.getUserName() + " leave from group");
	}
}
