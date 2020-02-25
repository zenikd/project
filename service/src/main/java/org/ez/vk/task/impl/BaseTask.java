package org.ez.vk.task.impl;

import org.ez.vk.db.AccountDao;
import org.ez.vk.helpers.GroupHelper;
import org.ez.vk.helpers.GroupFilter;
import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class BaseTask {
    protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected AccountDao accountDao;

    @Autowired
    protected GroupHelper groupHelper;

    @Autowired
    protected GroupFilter groupFilter;

}
