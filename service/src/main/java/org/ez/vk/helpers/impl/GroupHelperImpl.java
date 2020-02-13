package org.ez.vk.helpers.impl;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupType;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.helpers.GroupHelper;
import org.ez.vk.helpers.impl.AbstractHelper;
import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupHelperImpl implements GroupHelper {
    @Autowired
    protected AccountService accountService;

    private final static Integer COUNT_GROUP = 100;
    private final static TransportClient transportClient = HttpTransportClient.getInstance();
    private final static VkApiClient vk = new VkApiClient(transportClient);

    @Override
    public List<Group> getListGroupsByTag(String tag) throws Exception {
        List<Group> listGroups = new ArrayList();
        List<AccountVk> listAccount = this.accountService.getAccountsByType(1, UserTypeEnum.SEARCHER.toString());
        UserActor userActor = listAccount.get(0).getUserActor();

        for (int offset = 0; offset < COUNT_GROUP; offset += 100) {
            for (Group group : vk.groups().search(userActor, tag).count(100).offset(offset).execute()
                    .getItems()) {
                listGroups.add(group);
            }

            Thread.sleep(1100);
        }

        return listGroups;
    }

    @Override
    public String getGroupUrl(Group group) {
        String groupPrefix = group.getType() == GroupType.GROUP ? "club" : "public";
        return "https://vk.com/" + groupPrefix + group.getId();
    }
}
