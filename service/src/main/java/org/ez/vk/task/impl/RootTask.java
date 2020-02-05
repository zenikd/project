package org.ez.vk.task.impl;

import java.util.List;

import org.ez.vk.db.AccountDao;
import org.ez.vk.entity.db.constant.AccountConst;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.entity.query.SearchDTOQuery;
import org.ez.vk.entity.query.constant.Operators;
import org.ez.vk.entity.query.update.reserve.account.ReserveAccountDTOQuery;
import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.WebHelper;
import org.ez.vk.helpers.GroupHelper;
import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class RootTask {
    protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected AccountDao accountDao;

    @Autowired
    protected GroupHelper groupHelper;

}
