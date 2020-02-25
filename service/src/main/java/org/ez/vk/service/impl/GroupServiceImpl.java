package org.ez.vk.service.impl;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.account.UserSettings;
import org.ez.vk.db.AccountDao;
import org.ez.vk.db.GroupDao;
import org.ez.vk.entity.AccountServiceDTO;
import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.entity.db.GroupEntity;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.factory.AccountFactory;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.exception.user.BadCredentialsException;
import org.ez.vk.exception.user.RootUserException;
import org.ez.vk.helper.web.WebHelper;
import org.ez.vk.helpers.GroupHelper;
import org.ez.vk.service.AccountService;
import org.ez.vk.service.GroupService;
import org.json.JSONObject;
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
}
