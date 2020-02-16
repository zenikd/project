package org.ez.vk.helpers.impl;

import com.vk.api.sdk.objects.groups.Group;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helpers.GroupFilter;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.service.impl.account.AccountServiceImpl;
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
    AccountServiceImpl accountService;

    @Override
    public List<GroupFilterResult> filterGroup(FullGroupFilterCriteria groupFilterCriteria, List<Integer> groups) throws InternalException {
        CopyOnWriteArrayList<GroupFilterResult> groupFilterResults = new CopyOnWriteArrayList<>();
        List<AccountVk> accountVkList = accountService.getAccountsByType(999, UserTypeEnum.WORKING.toString());
        ExecutorService eService = Executors.newFixedThreadPool(accountVkList.size());
        AtomicInteger groupIndex = new AtomicInteger(0);
        System.out.println(accountVkList.size());
        accountVkList.forEach(accountVk -> {
            eService.execute(new GroupFilterThread(groupFilterCriteria, groups, groupFilterResults, groupIndex, accountVk.getUserActor()));
        });
        eService.shutdown();
        while (!eService.isTerminated()) {

        }
        return groupFilterResults;
    }
}

