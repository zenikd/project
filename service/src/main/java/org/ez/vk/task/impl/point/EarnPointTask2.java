package org.ez.vk.task.impl.point;

import java.util.List;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.RootTask;
import org.ez.vk.task.impl.point.exception.TaskAbsentException;
import org.ez.vk.task.impl.point.exception.TaskReservedException;
import org.ez.vk.task.impl.point.task.AddFriendTask;
import org.ez.vk.task.impl.point.task.JoinToGroupTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class EarnPointTask2 extends RootTask {
	@Autowired
	LikestAuthorizer likestAuthorizer;
	@Autowired
	LikestTaskHelper taskHelper;
	@Autowired
	BalanceUtil balanceUtil;
	@Autowired
	JoinToGroupTask joinToGroupTask;
	@Autowired
	AddFriendTask addFriendTask;

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(4);
		int curAcc = 0;
		while (true) {

			AccountVk accountVk = listAccount.get(curAcc % listAccount.size());
			curAcc++;
			if (accountVk.getLikestSiteToken() == null) {
				likestAuthorizer.authorize(accountVk);
				accountDao.updateEntity(accountVk);

			}
			startTask(accountVk);
			if (balanceUtil.getBalance(accountVk) >= 100) {
				balanceUtil.balanceTransfer(accountVk);
				System.out.println(accountVk.getUserName() + " balance transfer");
			}

		}

	}

	private void startTask(AccountVk accountVk) throws InternalException {
		TaskParam addFriendTaskParam = taskHelper.getOid("http://likest.ru/api/orders.getFriends", accountVk);
		if (addFriendTaskParam.getReward() == 0) {
			throw new TaskAbsentException();
		}

		try {

			addFriendTask.execute(accountVk, addFriendTaskParam);

		} catch (TaskReservedException e) {
			startTask(accountVk);
		}
	}

}
