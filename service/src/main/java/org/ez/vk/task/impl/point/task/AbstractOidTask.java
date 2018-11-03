package org.ez.vk.task.impl.point.task;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.BalanceUtil;
import org.ez.vk.task.impl.point.LikestTaskHelper;
import org.ez.vk.task.impl.point.TaskParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiAuthException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public abstract class AbstractOidTask {
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
	@Autowired
	LikestTaskHelper taskHelper;

	public void execute(AccountVk accountVk, TaskParam taskParam) throws InternalException {
		try {
			Thread.sleep(5000);
			taskBody(accountVk, taskParam);

		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}

	}

	protected abstract void taskBody(AccountVk accountVk, TaskParam taskParam)
			throws ApiException, ClientException, InterruptedException, InternalException;

}
