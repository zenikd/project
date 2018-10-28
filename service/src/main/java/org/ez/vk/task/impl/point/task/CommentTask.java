package org.ez.vk.task.impl.point.task;

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

	@Override
	protected void taskBody(AccountVk accountVk)
			throws ApiException, ClientException, InterruptedException, InternalException {
		Thread.sleep(5000);
		CommentParam commentParam;
		try {
			commentParam = taskHelper.getCommentTask(accountVk);
		} catch (InternalException e) {
			Thread.sleep(3000);
			execute(accountVk);
			return;
		}
	
		if (commentParam.getStatus().equals("ERR_NO_ORDERS")) {
			throw new TaskAbsentException();
		}
		System.out.println(accountVk.getUserName() + " start comment task");
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
		System.out.println("sleep");
		Thread.sleep(120000);

		System.out.println(accountVk.getUserName() + " over comment task");

	}

}
