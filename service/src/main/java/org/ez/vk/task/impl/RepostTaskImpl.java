package org.ez.vk.task.impl;

import java.util.ArrayList;
import java.util.List;

import org.ez.db.api.dao.IAccountDao;
import org.ez.db.api.dao.IGroupDao;
import org.ez.vk.dao.common.constant.db.filed.reservable.AccountConst;
import org.ez.vk.dao.common.constant.search.Operators;
import org.ez.vk.dao.common.entity.db.GroupEntity;
import org.ez.vk.dao.common.entity.db.reservable.AccountVk;
import org.ez.vk.dao.common.entity.query.reserv.AccountSearchDTO;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.dao.common.exception.user.RootUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;

@Service
public class RepostTaskImpl implements org.ez.vk.task.RepostTask
{
	private final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	private final static String WORKING = "workin";
	private final static Integer COUNT_GROUP = 100;
	@Autowired
	IAccountDao accountDao;
	@Autowired
	IGroupDao groupDao;

	public void findPostToRepost(String groupName, int count) throws InternalException {
		AccountSearchDTO accountSearchDTO = getListAccount();
		List<AccountVk> listAccount = accountDao.select(accountSearchDTO);
	}

	public void addNewGroupToFound(String town) throws InternalException, RootUserException {
		try {
			List<GroupEntity> listGroups = new ArrayList<GroupEntity>();
			AccountSearchDTO accountSearchDTO = getListAccount();
			List<AccountVk> listAccount = accountDao.select(accountSearchDTO);
			UserActor userActor = listAccount.get(0).getUserActor();
			for (int offset = 0; offset < COUNT_GROUP; offset += 100) {
				for (Group group : vk.groups().search(userActor, town).count(100).offset(offset).execute().getItems()) {
					listGroups.add(convetGroup(group, town));
				}

				Thread.sleep(1100);

			}
			groupDao.addListEntity(listGroups);
		} catch (ApiException e1) {
			throw new InternalException();
		} catch (ClientException e1) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}

	}

	private GroupEntity convetGroup(Group group, String town) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setId(group.getId());
		groupEntity.setType(group.getType().toString());
		groupEntity.setTown(town);
		return groupEntity;
	}

	private AccountSearchDTO getListAccount() {
		AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
		accountSearchDTO
			.setLimit(10)
			.getSearchQuery().addSearchParam(AccountConst.TYPE, Operators.$EQ, WORKING);
			
		return accountSearchDTO;
	}

}
