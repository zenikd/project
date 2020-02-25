package org.ez.vk.task.impl.point.task;

import org.ez.vk.entity.db.AccountVk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VkSearch {
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

	public static void sleep(int count, AccountVk accountVk) {
		for (int i = 0; i < count; i++) {
			try {
				vk.wall().get(accountVk.getUserActor()).execute();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
