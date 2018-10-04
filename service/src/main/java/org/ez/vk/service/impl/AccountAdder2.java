package org.ez.vk.service.impl;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
/**
 * Hello world!
 *
 */
public class AccountAdder2 {

	public static void main(String[] args) throws ApiException, ClientException, InterruptedException {
		TransportClient transportClient = HttpTransportClient.getInstance();
		VkApiClient vk = new VkApiClient(transportClient);

		UserAuthResponse authResponse = vk.oauth().userAuthorizationCodeFlow(6485623, "Eu2LdpxE3QOjaw5fN1zs",
				"https://artemsannikov.ru", "99114f420b62c97bf2").execute();

		UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

		System.out.println( authResponse.getAccessToken());

	}
	
	

	

}
