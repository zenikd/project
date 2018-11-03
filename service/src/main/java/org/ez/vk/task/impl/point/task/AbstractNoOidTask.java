package org.ez.vk.task.impl.point.task;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.point.LikestTaskHelper;
import org.ez.vk.task.impl.point.exception.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiPollsAccessException;
import com.vk.api.sdk.exceptions.ApiWallAccessAddReplyException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public abstract class AbstractNoOidTask {
	@Autowired
	LikestTaskHelper taskHelper;
	
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());	
	private static final Logger log = Logger.getLogger(AddFriendTask.class);

	public void execute(AccountVk accountVk) throws InternalException {
		try {
			taskBody(accountVk);
		} catch (ApiWallAccessAddReplyException e) {
			throw new AccessDeniedException();
		} catch (ApiPollsAccessException e) {
			log.info("Poll access denied:");
			throw new AccessDeniedException();
		} catch (ApiException e) {
			throw new InternalException();
		} catch (ClientException e) {
			throw new InternalException();
		} catch (InterruptedException e) {
			throw new InternalException();
		}

	}

	protected abstract void taskBody(AccountVk accountVk)
			throws ApiException, ClientException, InterruptedException, InternalException;
}
