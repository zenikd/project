package org.ez.vk.task.impl;

import java.util.ArrayList;
import java.util.List;

import org.ez.vk.db.AccountDao;
import org.ez.vk.db.GroupDao;
import org.ez.vk.entity.db.GroupEntity;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.search.FullSearchQuery;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;
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
	AccountDao accountDao;
	@Autowired
	GroupDao groupDao;

	public void findPostToRepost(String groupName, int count) throws InternalException {
		FullSearchQuery accountSearchDTO = getListAccount();
		List<AccountVk> listAccount = accountDao.select(accountSearchDTO);
	}

	public void addNewGroupToFound(String town) throws InternalException, RootUserException {
		try {
			List<GroupEntity> listGroups = new ArrayList<GroupEntity>();
			FullSearchQuery accountSearchDTO = getListAccount();
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

	private FullSearchQuery getListAccount() {
		FullSearchQuery accountSearchDTO = new FullSearchQuery();
		accountSearchDTO
			.setLimit(10)
			.getSearchQuery().addSearchParam(AccountConst.TYPE, Operators.$EQ, WORKING);
			
		return accountSearchDTO;
	}

}
