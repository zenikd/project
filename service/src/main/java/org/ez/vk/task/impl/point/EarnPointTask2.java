package org.ez.vk.task.impl.point;

import java.util.List;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.service.AccountService;
import org.ez.vk.task.impl.BaseTask;
import org.ez.vk.task.impl.point.exception.AccessDeniedException;
import org.ez.vk.task.impl.point.exception.TaskAbsentException;
import org.ez.vk.task.impl.point.exception.TaskReservedException;
import org.ez.vk.task.impl.point.task.AddFriendTask;
import org.ez.vk.task.impl.point.task.CommentTask;
import org.ez.vk.task.impl.point.task.JoinToGroupTask;
import org.ez.vk.task.impl.point.task.PollTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarnPointTask2 extends BaseTask {
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
	@Autowired
	PollTask pollTask;
	@Autowired
	JoinToGroupTask joinToGroupTask;
	@Autowired
	AccountService accountService;

	private static final Logger log = Logger.getLogger(EarnPointTask2.class);
	private int countTask = 0;

	public void earn() throws InternalException {
		List<AccountVk> listAccount = this.accountService.getAccountsByType(10);
		int curAcc = 0;
		while (true) {

			AccountVk accountVk = listAccount.get(curAcc % listAccount.size());
			curAcc++;
			if (accountVk.getLikestSiteToken() == null) {
				likestAuthorizer.authorize(accountVk);
				accountDao.updateEntity(accountVk);

			}
			startJoinGroupTask(accountVk);

		}

	}

	private void startJoinGroupTask(AccountVk accountVk) throws InternalException {

		TaskParam joinGroupTaskParam = taskHelper.getOid("http://likest.ru/api/orders.getGroups", accountVk);
		if (joinGroupTaskParam.getReward() == 0) {
			return;
		}

		try {

			joinToGroupTask.execute(accountVk, joinGroupTaskParam);

		} catch (TaskReservedException e) {
			startAddFriendTask(accountVk);
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
				balanceUtil.balanceTransfer(accountVk);				
			}

		} catch (TaskReservedException e) {
			startAddFriendTask(accountVk);
		}
	}

	private void startPollTask(AccountVk accountVk) throws InternalException {
		try {

			pollTask.execute(accountVk);
			Integer startBalance = balanceUtil.getBalance(accountVk);
			try {
				Thread.sleep(90000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Integer overBalance = balanceUtil.getBalance(accountVk);
			if (startBalance != overBalance) {
				log.info("Balance edited         !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if (overBalance >= 14) {
				balanceUtil.balanceTransfer(accountVk);				
			}
			countTask++;
			log.info("Task over:" + countTask);
		} catch (TaskAbsentException e) {
			log.info(accountVk.getUserName() + " isn't have poll task");
			return;
		} catch (AccessDeniedException e2) {
			log.info(accountVk.getUserName() + " isn't have access to add poll");
			return;
		}
	}

	private void startCommentTask(AccountVk accountVk) throws InternalException {
		try {
			commentTask.execute(accountVk);
			Integer balance = balanceUtil.getBalance(accountVk);
			if (balance >= 14) {
				balanceUtil.balanceTransfer(accountVk);				
			}
			countTask++;
			log.info("Task over:" + countTask);
		} catch (TaskAbsentException e) {
			log.info(accountVk.getUserName() + " isn't have comment task");
			startPollTask(accountVk);
		} catch (AccessDeniedException e2) {
			log.info(accountVk.getUserName() + " isn't have access to add comment");
			return;
		}
	}

}
