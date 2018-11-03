package org.ez.vk.task.impl.point.task;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.TextHelper;
import org.ez.vk.task.impl.point.entity.PollParam;
import org.ez.vk.task.impl.point.exception.TaskAbsentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class PollTask extends AbstractNoOidTask {
	@Autowired
	TextHelper textHelper;
	
	private static final Logger log = Logger.getLogger(PollTask.class);

	@Override
	protected void taskBody(AccountVk accountVk)
			throws ApiException, ClientException, InterruptedException, InternalException {
		log.info(accountVk.getUserName() + " start poll task");
		Thread.sleep(2000);
		PollParam pollParam;
		try {
			pollParam = taskHelper.getPollTask(accountVk);
		} catch (InternalException e) {
			Thread.sleep(3000);
			execute(accountVk);
			return;
		}

		if (pollParam.getStatus().equals("ERR_NO_ORDERS")) {
			log.info("Poll task isn't available in accoun " + accountVk.getUserName());
			throw new TaskAbsentException();
		}
		

		vk.polls()
				.addVote(accountVk.getUserActor(), Integer.parseInt(pollParam.getPoll_id()),
						Integer.parseInt(pollParam.getPoll_answer()))
				.ownerId(Integer.parseInt(pollParam.getPoll_owner())).execute();

		log.info(accountVk.getUserName() + " add poll and start sleep");
		VkSearch.sleep(12, accountVk);

		log.info(accountVk.getUserName() + " over poll task");

	}

}
