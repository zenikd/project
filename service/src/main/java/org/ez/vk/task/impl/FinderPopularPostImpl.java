package org.ez.vk.task.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
import org.ez.vk.task.FinderPopularPost;
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
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.groups.GroupsSearchSort;
import com.vk.api.sdk.queries.market.MarketGetByIdQuery;
import com.vk.api.sdk.queries.market.MarketGetQuery;

@Service
public class FinderPopularPostImpl extends RootTask implements FinderPopularPost
{
	private final static Integer COUNT_GROUP = 100;
	private final static Integer COUNT_ACCOUNT = 1;


	public void getListPost(String tag) {
		try {
			List<Group> listGroups = new ArrayList<Group>();
			List<AccountVk> listAccount = getListWorkAccount(COUNT_ACCOUNT);
			UserActor userActor = listAccount.get(0).getUserActor();
			
			for (int offset = 0; offset < COUNT_GROUP; offset += 100) {
				for (Group group : vk.groups().search(userActor, tag).count(50).offset(offset).execute().getItems()) {
					listGroups.add(group);
				}

				Thread.sleep(1100);
			}
			
			List<PostWithValue> list = new ArrayList();
			for(int i = 0; i < 100; i++) {
				list.add(new PostWithValue());
			}
			
			for (int number = 0; number < listGroups.size(); number++) {
				try {	
				
				Group group = listGroups.get(number);
				
				if(group.getType() == GroupType.GROUP) continue;
				
				int groupId = -group.getId();
				 Thread.sleep(1100);
			     List<WallPostFull> wallPostFull = vk.wall().get(userActor).count(100).ownerId(groupId).execute().getItems();
			     
			     if(wallPostFull.size() < 10) continue;
			     
			     List<WallPostFull> postsWithoutPinned = wallPostFull.stream().filter(post -> post != null && post.getIsPinned() == null).collect(Collectors.toList());
			     
			     int postCount = postsWithoutPinned.size();
			     
			     float likesCount = (float) postsWithoutPinned.stream().map(post -> post.getLikes().getCount()).reduce(0, (sum, value) -> sum + value);
			     if(likesCount < 100) continue;
			     
			     float avg = likesCount / postCount;
			     
			     for(WallPostFull post:  postsWithoutPinned) {
			    	 
			    	float value = post.getLikes().getCount() / avg;
			    	 if(list.get(99).value <  value ) {
			    		 PostWithValue postWithValue = new PostWithValue();
			    		 postWithValue.value = value;
			    		 String groupPrefix = group.getType() == GroupType.GROUP ? "club" : "public";
			    		 postWithValue.url = "https://vk.com/" + groupPrefix + group.getId() + "?w=wall-" + group.getId() + "_" + post.getId();
			    		 list.set(99, postWithValue);
			    		 Collections.sort(list, (d1, d2) -> {
			    				return (int)(d2.value * 100 - d1.value * 100);
			    			});
			    	 }			    	
			     }
				} catch (Exception e) {
					// TODO: handle exception
				}
			     
			     
			}
			
			list.stream().forEach(post -> System.out.println(post.url) );

	} catch (Exception e) {
		// TODO: handle exception
	}
	}
}


class PostWithValue {
	float value;
	String url;
}
