package org.ez.vk.task.impl.point;

import java.util.List;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.RootTask;
import org.ez.vk.task.impl.point.exception.AccessDeniedException;
import org.ez.vk.task.impl.point.exception.TaskAbsentException;
import org.ez.vk.task.impl.point.exception.TaskReservedException;
import org.ez.vk.task.impl.point.task.AddFriendTask;
import org.ez.vk.task.impl.point.task.CommentTask;
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
	AddFriendTask addFriendTask;
	@Autowired
	CommentTask commentTask;

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(10);
		int curAcc = 0;
		while (true) {

			AccountVk accountVk = listAccount.get(curAcc % listAccount.size());
			curAcc++;
			if (accountVk.getLikestSiteToken() == null) {
				likestAuthorizer.authorize(accountVk);
				accountDao.updateEntity(accountVk);

			}
			startCommentTask(accountVk);

		}

	}

	private void startAddFriendTask(AccountVk accountVk) throws InternalException {

		TaskParam addFriendTaskParam = taskHelper.getOid("http://likest.ru/api/orders.getFriends", accountVk);
		if (addFriendTaskParam.getReward() == 0) {
			return;
		}

		try {

			addFriendTask.execute(accountVk, addFriendTaskParam);
			Integer balance = balanceUtil.getBalance(accountVk);
			if (balance >= 5) {
				balanceUtil.balanceTransfer(accountVk, balance);
				System.out.println(accountVk.getUserName() + " balance transfer");
			}

		} catch (TaskReservedException e) {
			startAddFriendTask(accountVk);
		}
	}

	private void startCommentTask(AccountVk accountVk) throws InternalException {
		try {
			commentTask.execute(accountVk);
			Integer balance = balanceUtil.getBalance(accountVk);
			if (balance >= 100) {
				balanceUtil.balanceTransfer(accountVk, balance);
				System.out.println(accountVk.getUserName() + " balance transfer");
			}
		} catch (TaskAbsentException e) {
			return;
		} catch (AccessDeniedException e2) {
			return;
		}
	}

}
