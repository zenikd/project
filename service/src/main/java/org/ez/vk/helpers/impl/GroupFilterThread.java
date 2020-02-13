package org.ez.vk.helpers.impl;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.groups.GroupField;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.filter.PostFilter;
import org.ez.vk.helpers.impl.model.GroupFilterResult;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GroupFilterThread extends Thread {
    final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
    FullGroupFilterCriteria groupFilterCriteria;
    List<Group> groups;
    CopyOnWriteArrayList<GroupFilterResult> groupFilterResults;
    AtomicInteger groupIndex;
    UserActor userActor;

    GroupFilterThread(FullGroupFilterCriteria groupFilterCriteria, List<Group> groups, CopyOnWriteArrayList<GroupFilterResult> groupFilterResults, AtomicInteger groupIndex, UserActor userActor) {
        this.groupFilterCriteria = groupFilterCriteria;
        this.groups = groups;
        this.groupFilterResults = groupFilterResults;
        this.groupIndex = groupIndex;
        this.userActor = userActor;
    }

    public void run() {
        try {
            while (groups.size() > groupIndex.intValue()) {
                Group group = groups.get(groupIndex.getAndIncrement());
                System.out.println(groupIndex.intValue());
                Thread.sleep(1100);

                if (groupFilterCriteria.getPostFilter() != null) {
                    if (!checkPostCriteria(group)) {
                        continue;
                    }
                }

                GroupFilterResult groupFilterResult = new GroupFilterResult();
                groupFilterResult.setGroup(group);

                try {
                    if (groupFilterCriteria.isAddAdminsToResponse()) {
                        Thread.sleep(1100);
                        GroupFull groupFull = vk.groups()
                                .getById(userActor)
                                .groupIds(group.getId().toString())
                                .fields(GroupField.CONTACTS)
                                .execute()
                                .get(0);
                        if(groupFull.getContacts().size() == 0) continue;

                        List<Integer> adminsIds = groupFull
                                .getContacts()
                                .stream()
                                .filter(contact -> contact.getUserId() != null)
                                .mapToInt(contact -> contact.getUserId())
                                .boxed()
                                .collect(Collectors.toList());
                        if(adminsIds.size() == 0) continue;

                        groupFilterResult.setAdminIds(adminsIds);
                    }

                } catch (ApiException e) {
                    continue;
                }

                groupFilterResults.add(groupFilterResult);
            }
        } catch (ClientException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean checkPostCriteria(Group group) throws ClientException {
        PostFilter postFilter = groupFilterCriteria.getPostFilter();
        try {
            if (postFilter.isSearchByLastPostDate()) {

                if (postFilter.isSearchByLastPostDate()) {

                    List<WallPostFull> wallPostFullList = vk.wall().get(userActor).count(2).ownerId(-group.getId()).execute().getItems();
                    WallPostFull wallPostFull = wallPostFullList.get(0).getIsPinned() == null ? wallPostFullList.get(0) : wallPostFullList.get(1);

                    long creationDate = wallPostFull.getDate();

                    Date referenceDate = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(referenceDate);
                    c.add(Calendar.DATE, -postFilter.getDay());

                    long currenDate = c.getTime().getTime() / 1000;

                    boolean isCorrect = postFilter.isEarlier() ? creationDate > currenDate : creationDate < currenDate;

                    if (!isCorrect) {
                        return false;
                    }
                }
            }

            return true;

        } catch (ApiException e) {
            return false;
        }
    }
}
