package org.ez.vk.helpers.impl;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupType;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.helpers.GroupHelper;
import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupHelperImpl implements GroupHelper {
    @Autowired
    protected AccountService accountService;

    private final static Integer COUNT_GROUP = 100;
    private final static TransportClient transportClient = HttpTransportClient.getInstance();
    private final static VkApiClient vk = new VkApiClient(transportClient);

    @Override
    public List<Integer> getListGroupsByTag(String tag, int count, List<String> incompatibleTags) throws Exception {
        List<Integer> listGroups = new ArrayList<>();
        List<AccountVk> listAccount = this.accountService.getAccountsByType(1, UserTypeEnum.SEARCHER.toString());
        UserActor userActor = listAccount.get(0).getUserActor();

        for (int offset = 0; offset < count; offset += 1000) {
            List<Group> groups = vk.groups().search(userActor, tag).count(1000).offset(offset).execute()
                    .getItems();

            groups.stream()
                    .filter(group -> group.getIsClosed().getValue() == 0)
                    .filter(group -> incompatibleTags
                            .stream()
                            .noneMatch(incompatibleTag -> group.getName().toLowerCase().contains(incompatibleTag)))
                    .forEach(group -> listGroups.add(group.getId()));

            Thread.sleep(1100);
        }

        return listGroups.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Integer> getListGroupIdsFromFile() throws IOException {
        List<Integer> groups = new ArrayList<>();
        Path pathToFile = Paths.get("groupsIds.txt");

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            while (line != null) {
                groups.add(Integer.parseInt(line));
                line = br.readLine();
            }

        }
        return groups;
    }

    @Override
    public List<Integer> getListGroupsByTag(String tag) throws Exception {
        return this.getListGroupsByTag(tag, COUNT_GROUP, new ArrayList<>());
    }

    @Override
    public String getGroupUrl(int groupId) {
        return "https://vk.com/public" + groupId;
    }
}
