package org.ez.vk.helpers.impl;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.groups.GroupField;
import org.ez.vk.helpers.ExecutorHelper;
import org.ez.vk.helpers.impl.model.GroupFilterThreadParam;
import org.ez.vk.helpers.impl.model.GroupIdWithPosts;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.filter.PostFilter;
import org.ez.vk.helpers.impl.model.GroupFilterResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GroupFilterThread extends Thread {
    final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

    FullGroupFilterCriteria groupFilterCriteria;
    List<Integer> groupsIdsToFilter;
    UserActor userActor;
    ExecutorHelper executorHelper;

    AtomicInteger groupIndexToPost = new AtomicInteger(0);
    AtomicInteger groupIndexToAdmin = new AtomicInteger(0);

    CopyOnWriteArrayList<GroupFilterResult> groupFilterResults = new CopyOnWriteArrayList();

    GroupFilterThread(GroupFilterThreadParam groupFilterThreadParam) {
        this.groupFilterCriteria = groupFilterThreadParam.getGroupFilterCriteria();
        this.groupsIdsToFilter = groupFilterThreadParam.getGroupsIds();
        this.groupFilterResults = groupFilterThreadParam.getGroupFilterResults();
        this.groupIndexToAdmin = groupFilterThreadParam.getGroupIndexToAdmin();
        this.groupIndexToPost = groupFilterThreadParam.getGroupIndexToPost();
        this.userActor = groupFilterThreadParam.getUserActor();
        this.executorHelper = groupFilterThreadParam.getExecutorHelper();
    }

    public void run() {
        try {
            this.loadGroupIdWithPosts();
            if(true) {
                return;
            }
            while (groupsIdsToFilter.size() > groupIndexToAdmin.intValue()) {
                Integer groupId = groupsIdsToFilter.get(groupIndexToAdmin.getAndIncrement());
                System.out.println(groupIndexToAdmin.intValue());
                Thread.sleep(300);

                GroupFilterResult groupFilterResult = new GroupFilterResult();
                //Todo
                //groupFilterResult.setGroup(group);

                try {
                    if (groupFilterCriteria.isAddAdminsToResponse()) {
                        Thread.sleep(350);
                        GroupFull groupFull = vk.groups()
                                .getById(userActor)
                                .groupIds(groupId.toString())
                                .fields(GroupField.CONTACTS)
                                .execute()
                                .get(0);
                        if (groupFull.getContacts() == null || groupFull.getContacts().size() == 0) continue;

                        List<Integer> adminsIds = groupFull
                                .getContacts()
                                .stream()
                                .filter(contact -> contact.getUserId() != null)
                                .mapToInt(contact -> contact.getUserId())
                                .boxed()
                                .collect(Collectors.toList());
                        if (adminsIds.size() == 0 || adminsIds.size() > 3) continue;

                        groupFilterResult.setAdminIds(adminsIds);
                    }

                } catch (ApiException e) {
                    continue;
                }

                groupFilterResults.add(groupFilterResult);
            }
        } catch (ClientException | InterruptedException | ApiException e) {
            e.printStackTrace();
        }
    }

    void loadGroupIdWithPosts() throws ClientException, ApiException, InterruptedException {
        while (groupsIdsToFilter.size() > groupIndexToPost.intValue()) {
            Thread.sleep(350);
            List<Integer> groupIdsToSearch = new ArrayList();
            int currentGroup = 0;

            while ( currentGroup < 25) {
                int groupIndex = groupIndexToPost.getAndIncrement();
                if(groupsIdsToFilter.size() <= groupIndex) {
                    break;
                }
                groupIdsToSearch.add(groupsIdsToFilter.get(groupIndex));
                currentGroup++;
            }

            System.out.println(groupIndexToPost.intValue());

            List<GroupIdWithPosts> groupIdWithPosts = this.executorHelper.getGroupPosts(groupIdsToSearch, userActor);
            List<GroupFilterResult> groupFilterResults = groupIdWithPosts.stream()
                    .filter(item -> this.isGroupCompatibleByPosts(item.getPosts()))
                    .map(item -> {
                        GroupFilterResult groupFilterResult = new GroupFilterResult();
                        groupFilterResult.setGroupId(item.getGroupId());
                        groupFilterResult.setPosts(item.getPosts());
                        return groupFilterResult;
                    })
                    .collect(Collectors.toList());

            this.groupFilterResults.addAll(groupFilterResults);
        }
    }

    boolean isGroupCompatibleByPosts(List<WallPostFull> wallPostFullList) {
        if(groupFilterCriteria.getPostFilter() == null) {
            return true;
        }

        PostFilter postFilter = groupFilterCriteria.getPostFilter();

        if (wallPostFullList.size() < postFilter.getMinAmountPosts()) {
            return false;
        }

        if (postFilter.isSearchByLastPostDate()) {


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

        if(isCriteriaEnabled(postFilter.getMinAverageLikes())) {
            int amountLikes = wallPostFullList
                    .stream()
                    .mapToInt(post -> {
                        return  post.getLikes() != null ?  post.getLikes().getCount() : 0;
                    })
                    .reduce(0, (likes, sum) -> likes + sum);
            float amountPosts = wallPostFullList.size();
            if(amountLikes / amountPosts < postFilter.getMinAverageLikes()) {
                return false;
            }
        }

        return true;

    }

    boolean isCriteriaEnabled(int value) {
        return value != 0 ;
    }
}
