package org.ez.vk.service.task.repost;

import java.util.ArrayList;
import java.util.List;

import org.ez.api.dao.IAccountDao;
import org.ez.api.dao.IGroupDao;
import org.ez.vk.dao.common.entity.db.GroupEntity;
import org.ez.vk.dao.common.entity.db.reserved.AccountVk;
import org.ez.vk.dao.common.entity.search.reserved.AccountSearchDTO;
import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.service.api.IRepostTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;

@Service
public class RepostTask implements IRepostTask {
	private final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	private final static String WORKING = "workingg";
	private final static Integer COUNT_GROUP = 100;
	@Autowired
	IAccountDao accountDao;
	@Autowired
	IGroupDao groupDao;

	public void findPostToRepost(String groupName, int count) throws InternalException {
		AccountSearchDTO accountSearchDTO = getListAccount();
		List<AccountVk> listAccount = accountDao.select(accountSearchDTO);
	}

	public void addNewGroupToFound(String town) throws InternalException {
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
			groupDao.addGroups(listGroups);
		} catch (ApiException e1) {
			throw new InternalException();
		} catch (ClientException e1) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}

		return "ok";

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
			.getSearchQuery().addSearchParam(filed, operator, value).
			
		return accountSearchDTO;
	}

}
