package org.ez.vk.service.impl;

import org.ez.vk.db.GroupDao;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.entity.db.GroupEntity;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.constant.GroupConst;
import org.ez.vk.entity.query.GroupSearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helpers.GroupHelper;
import org.ez.vk.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupDao groupDao;
    @Autowired
    GroupHelper groupHelper;

    public void addGroupsFromFile(String tag) throws IOException {
        List<Integer> groupIds =  groupHelper.getListGroupIdsFromFile();
        List<GroupEntity> groupEntities = groupIds.stream()
                .map(id -> {
                    GroupEntity groupEntity = new GroupEntity();
                    groupEntity.setTag(tag);
                    groupEntity.setId(id);
                    return groupEntity;
                })
                .collect(Collectors.toList());
        groupDao.addListEntity(groupEntities);
    }

    public List<Integer> getGroupsIdsByTag(String tag) throws InternalException {
        GroupSearchDTOQuery groupSearchDTOQuery = new GroupSearchDTOQuery();
        groupSearchDTOQuery
                .setLimit(999)
                .getSearchQuery().addQueryParam(GroupConst.TAG, Operators.$EQ, tag);
        return groupDao.select(groupSearchDTOQuery)
                .stream()
                .map(groupEntity -> groupEntity.getId())
                .collect(Collectors.toList());
    }
}
