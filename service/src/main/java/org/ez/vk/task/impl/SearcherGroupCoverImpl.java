package org.ez.vk.task.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.task.SearcherGroupCover;
import org.springframework.stereotype.Service;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiAuthException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Cover;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupType;
import com.vk.api.sdk.objects.market.MarketItem;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.groups.GroupField;

@Service
public class SearcherGroupCoverImpl extends RootTask implements SearcherGroupCover {
    private final static Integer COUNT_GROUP = 1000;
    private final static Integer COUNT_ACCOUNT = 10;

    public void getListGroup(String tagss) {

        String[] tags = {"создание сайтов", "разработка сайтов", "копирайтинг", "seo", "smm"};

        for (String tag : tags) {

            try {

                List<Group> listGroups = this.groupHelper.getListGroupsByTag(tag);

                List<AccountVk> listAccount = this.accountService.getAccountsByType(COUNT_ACCOUNT, "analizaer");
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
