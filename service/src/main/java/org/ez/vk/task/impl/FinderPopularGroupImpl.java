package org.ez.vk.task.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ez.vk.db.AccountDao;
import org.ez.vk.db.GroupDao;
import org.ez.vk.entity.db.GroupEntity;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.SearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.task.FinderPopularGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.actions.Market;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupType;
import com.vk.api.sdk.objects.market.MarketItem;
import com.vk.api.sdk.objects.market.MarketItemFull;
import com.vk.api.sdk.queries.groups.GroupsSearchSort;
import com.vk.api.sdk.queries.market.MarketGetByIdQuery;
import com.vk.api.sdk.queries.market.MarketGetQuery;

@Service
public class FinderPopularGroupImpl extends RootTask implements FinderPopularGroup
{
	private final static Integer COUNT_GROUP = 1000;
	private final static Integer COUNT_ACCOUNT = 1;


	public void getListGroup(String tag) {
		try {
			List<AccountVk> listAccount = getListWorkAccount(COUNT_ACCOUNT);
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
