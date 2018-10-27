package org.ez.vk.task.impl.point.task;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.BalanceUtil;
import org.ez.vk.task.impl.point.LikestTaskHelper;
import org.ez.vk.task.impl.point.TaskParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public abstract class AbstractTask {
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	@Autowired
	LikestTaskHelper taskHelper;
	
	public abstract void execute(AccountVk accountVk, TaskParam taskParam) throws InternalException;

}
