package org.ez.vk.task.impl.point.task;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.BalanceUtil;
import org.ez.vk.task.impl.point.TaskParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
@Service
public class JoinToGroupTask extends AbstractTask {

	public void execute(AccountVk accountVk, TaskParam taskParam) throws InternalException {
		try {
			Integer groupId = taskHelper.getTask(taskParam, accountVk);
			Thread.sleep(5000);
			System.out.println(accountVk.getUserName() + " join to group");
			vk.groups().join(accountVk.getUserActor()).groupId(groupId).execute();
			Thread.sleep(120000);
			vk.groups().leave(accountVk.getUserActor(), groupId).execute();
			System.out.println(accountVk.getUserName() + " leave from group");
		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}
	}
}
