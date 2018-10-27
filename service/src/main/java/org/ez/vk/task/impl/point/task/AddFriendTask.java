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
public class AddFriendTask extends AbstractTask {

	public void execute(AccountVk accountVk, TaskParam taskParam) throws InternalException {
		try {
			Thread.sleep(5000);
			Integer friendId = taskHelper.getTask(taskParam, accountVk);
			System.out.println(accountVk.getUserName() + " start friend task");
			vk.friends().add(accountVk.getUserActor(), friendId).execute();
			System.out.println("sleep");
			Thread.sleep(120000);

			vk.friends().delete(accountVk.getUserActor(), friendId).execute();
			System.out.println(accountVk.getUserName() + " remove friend");
		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}
	}
}
