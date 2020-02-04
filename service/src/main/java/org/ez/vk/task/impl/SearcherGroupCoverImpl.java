package org.ez.vk.task.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
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
import org.ez.vk.task.SearcherGroupCover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.actions.Market;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiAuthException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Cover;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.GroupType;
import com.vk.api.sdk.objects.market.MarketItem;
import com.vk.api.sdk.objects.market.MarketItemFull;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.objects.wall.responses.SearchResponse;
import com.vk.api.sdk.queries.groups.GroupField;
import com.vk.api.sdk.queries.market.MarketGetByIdQuery;
import com.vk.api.sdk.queries.market.MarketGetQuery;

@Service
public class SearcherGroupCoverImpl extends RootTask implements SearcherGroupCover {
	private final static Integer COUNT_GROUP = 1000;
	private final static Integer COUNT_ACCOUNT = 10;

	public void getListGroup(String tagss) {
		
		String[] tags = {"создание сайтов", "разработка сайтов", "копирайтинг", "seo", "smm"};

		for (String tag : tags) {

			try {
				this.validateAccounts("searcher");
				this.validateAccounts("analizaer");				
				
				
				List<Group> listGroups = new ArrayList<Group>();
				List<AccountVk> listAccount = getListWorkAccount(1, "searcher");
				UserActor userActor = listAccount.get(0).getUserActor();

				for (int offset = 0; offset < COUNT_GROUP; offset += 100) {
					for (Group group : vk.groups().search(userActor, tag).count(100).offset(offset).execute()
							.getItems()) {
						listGroups.add(group);
					}

					Thread.sleep(1100);
				}

				listAccount = getListWorkAccount(COUNT_ACCOUNT, "analizaer");
				int currentGroup = 0;
				while (currentGroup < listGroups.size()) {
					for (AccountVk actor : listAccount) {
						Group group = listGroups.get(currentGroup);
						CoverSearcher searcher = new CoverSearcher(group, actor.getUserActor());
						searcher.start();
						currentGroup++;
					}

					Thread.sleep(3300);
				}

			} catch (Exception e1) {
				System.out.println(e1);
			}
		}

	}
}

class CoverSearcher extends Thread {
	final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	Group group;
	UserActor userActor;

	CoverSearcher(Group group, UserActor userActor) {
		this.group = group;
		this.userActor = userActor;
	}

	public void run() {

		try {
			Integer groupId = -group.getId();

			List<MarketItem> items = vk.market().get(userActor, groupId).execute().getItems();

			if (items.size() == 0) {
				return;
			}

			Thread.sleep(1100);
			Cover cover = vk.groups().getById(userActor).fields(GroupField.COVER).groupId(group.getId().toString())
					.execute().get(0).getCover();
			if (cover == null && cover.getImages().size() == 0)
				return;

			Thread.sleep(1100);
			WallPostFull wallPostFull = vk.wall().get(userActor).count(2).ownerId(groupId).execute().getItems().get(1);

			long creationDate = wallPostFull.getDate();

			Date referenceDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(referenceDate);
			c.add(Calendar.MONTH, -9);

			long currenDate = c.getTime().getTime() / 1000;

			if (creationDate < currenDate) {
				String groupPrefix = group.getType() == GroupType.GROUP ? "club" : "public";
				System.out.println("https://vk.com/" + groupPrefix + group.getId());
			}

		} catch (ApiAuthException e1) {
			System.out.println(e1);
			System.out.println(userActor.getId());

		} catch (Exception e1) {
		}

	}
}
