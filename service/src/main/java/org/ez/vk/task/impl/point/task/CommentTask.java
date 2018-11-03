package org.ez.vk.task.impl.point.task;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.TextHelper;
import org.ez.vk.task.impl.point.entity.CommentParam;
import org.ez.vk.task.impl.point.exception.TaskAbsentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class CommentTask extends AbstractNoOidTask {
	@Autowired
	TextHelper textHelper;

	private static final Logger log = Logger.getLogger(CommentTask.class);

	@Override
	protected void taskBody(AccountVk accountVk)
			throws ApiException, ClientException, InterruptedException, InternalException {
		log.info(accountVk.getUserName() + " start comment task");
		Thread.sleep(2000);
		CommentParam commentParam;
		try {
			commentParam = taskHelper.getCommentTask(accountVk);
		} catch (InternalException e) {
			Thread.sleep(3000);
			execute(accountVk);
			return;
		}

		if (commentParam.getStatus().equals("ERR_NO_ORDERS")) {
			log.info("Comment task isn't available in accoun " + accountVk.getUserName());
			throw new TaskAbsentException();
		}
		if (commentParam.getObject_place().equals("wall")) {
			vk.wall().createComment(accountVk.getUserActor(), Integer.parseInt(commentParam.getId()[1]))
					.ownerId(Integer.parseInt(commentParam.getId()[0])).message(commentParam.getMessage()).execute();
		} else if (commentParam.getObject_place().equals("photos")) {
			vk.photos().createComment(accountVk.getUserActor(), Integer.parseInt(commentParam.getId()[1]))
					.ownerId(Integer.parseInt(commentParam.getId()[0])).message(commentParam.getMessage()).execute();
		} else if (commentParam.getObject_place().equals("board")) {
			vk.board().createComment(accountVk.getUserActor(), Integer.parseInt(commentParam.getId()[0]),
					Integer.parseInt(commentParam.getId()[0])).execute();
		}
		log.info(accountVk.getUserName() + " add comment and start sleep");
		VkSearch.sleep(12, accountVk);

		log.info(accountVk.getUserName() + " over comment task");

	}

}
