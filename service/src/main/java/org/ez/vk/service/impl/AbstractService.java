package org.ez.vk.service.impl;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class AbstractService {
	private final static TransportClient transportClient = HttpTransportClient.getInstance();
	private final static VkApiClient vk = new VkApiClient(transportClient);
}
