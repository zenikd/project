package org.ez.vk.helpers.impl;

import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helpers.ExecutorHelper;
import org.ez.vk.helpers.GroupFilter;
import org.ez.vk.helpers.impl.model.GroupFilterThreadParam;
import org.ez.vk.helpers.impl.model.GroupIdWithPosts;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GroupFilterImpl implements GroupFilter {
    @Autowired
    AccountService accountService;

    @Autowired
    ExecutorHelper executorHelper;

    @Override
    public List<GroupFilterResult> filterGroup(FullGroupFilterCriteria groupFilterCriteria, List<Integer> groupsIds) throws InternalException {
        List<AccountVk> accountVkList = accountService.getAccountsByType(999, UserTypeEnum.WORKING.toString());
        ExecutorService eService = Executors.newFixedThreadPool(accountVkList.size());
        AtomicInteger groupIndex = new AtomicInteger(0);
        System.out.println(accountVkList.size());

        AtomicInteger groupIndexToPost = new AtomicInteger(0);
        CopyOnWriteArrayList<GroupIdWithPosts> groupIdWithPosts = new CopyOnWriteArrayList<>();
        AtomicInteger groupIndexToAdmin = new AtomicInteger(0);
        CopyOnWriteArrayList<GroupFilterResult> groupFilterResults = new CopyOnWriteArrayList<>();

        accountVkList.forEach(accountVk -> {
            GroupFilterThreadParam groupFilterThreadParam = new GroupFilterThreadParam();
            groupFilterThreadParam.setGroupFilterCriteria(groupFilterCriteria);
            groupFilterThreadParam.setGroupsIds(groupsIds);
            groupFilterThreadParam.setUserActor(accountVk.getUserActor());
            groupFilterThreadParam.setExecutorHelper(executorHelper);
            groupFilterThreadParam.setGroupIndexToPost(groupIndexToPost);
            groupFilterThreadParam.setGroupIndexToAdmin(groupIndexToAdmin);
            groupFilterThreadParam.setGroupFilterResults(groupFilterResults);
            eService.execute(new GroupFilterThread(groupFilterThreadParam));
        });
        eService.shutdown();
        while (!eService.isTerminated()) {

        }
        return groupFilterResults;
    }
}

