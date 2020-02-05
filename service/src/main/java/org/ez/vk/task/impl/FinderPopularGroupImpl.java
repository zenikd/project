package org.ez.vk.task.impl;

import java.util.List;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.task.FinderPopularGroup;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupType;
import com.vk.api.sdk.queries.groups.GroupsSearchSort;

@Service
public class FinderPopularGroupImpl extends RootTask implements FinderPopularGroup
{
	private final static Integer COUNT_GROUP = 1000;
	private final static Integer COUNT_ACCOUNT = 1;


	public void getListGroup(String tag) {
		try {
			List<AccountVk> listAccount = this.accountService.getAccountsByType(COUNT_ACCOUNT);
			UserActor userActor = listAccount.get(0).getUserActor();
	
			for (Group group : vk.groups()
					.search(userActor, tag).count(100)
					.sort(GroupsSearchSort.BY_COMMENTS_NUMBER_PER_MEMBERS_NUMBER_RATIO)
					.execute()
					.getItems()) {
				String groupPrefix = group.getType() == GroupType.GROUP ? "club" : "public";
				System.out.println("https://vk.com/" + groupPrefix + group.getId());

			}		
			
			
		} catch (Exception e1) {
			
		} 

	}
}
