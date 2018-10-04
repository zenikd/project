package org.ez.vk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.responses.SearchResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

/**
 * Hello world!
 *
 */
public class App {
	private static int countGroup = 1000;

	public static void main(String[] args) throws ApiException, ClientException, InterruptedException {
		TransportClient transportClient = HttpTransportClient.getInstance();
		VkApiClient vk = new VkApiClient(transportClient);

		List<UserActor> listActros = getListActor();

		List<Group> listGroup = getListGroup(vk, listActros, countGroup);
		int i = 0;
		List<String> listPost = finderRepost(vk, listActros, listGroup);

		

		for (String postLink : listPost) {
			System.out.println(postLink);
		}

	}

	private static List<String> finderRepost(VkApiClient vk, List<UserActor> listActros, List<Group> listGroup)
			throws ApiException, ClientException, InterruptedException {

		List<String> listPost = new ArrayList();

		int i = 0;
		
		while (i < listGroup.size()) {
			
			for (UserActor actor : listActros) {
				Group group = listGroup.get(i);
				new GeterPost(listPost, i, group, vk, actor).start();
				i++;
				Thread.sleep(1150);

			}
			System.out.println("осталось " + (listGroup.size() - i));
			
		}

		return listPost;
	}

	private static List<UserActor> getListActor() {
		List<UserActor> listActros = new ArrayList<UserActor>();
		listActros.add(new UserActor(82297449,
				"06c14e440819aeaca1444b3593e9f5cb679dc9204003fbf89aa1ef736544ab05b0cc145f90ac3c4ee8487"));
		listActros.add(new UserActor(82297449,
				"c080eecdf31e867c7b1212b6a370a5bda48fbf758c19276cda4f8dfc3b3d02d55c05cbb2c4ed35240e9ba"));
		listActros.add(new UserActor(82297449,
				"88af944951924169e8af729448515bac77745f28873cdc47386771678cb462e71768def4624b86f6ddb0b"));
		listActros.add(new UserActor(82297449,
				"70f9273df0c63af090089d0d13d7d6c846a28279d5cb0be4f466b54acafc82748cceb905a35009957d9ec"));
		
		listActros.add(new UserActor(82297449,
				"8d36b569daf932d1b4897a178bcf41f57de202a597c199d9a9767053173d3ecfe04e5266aaedd8c3b5b5a"));
		listActros.add(new UserActor(82297449,
				"00aef6207cbbcbc550754ed6daa9b4c23c2e7e5c3f5d0b8ea7ad85de82ae1bc7ba979ae09f5a80047278a"));
		listActros.add(new UserActor(82297449,
				"e079b66f513359be8ed02b3b273c7652eeb7ee52bac05d671983453a852bac6c2339d3f3ba198a9c2218b"));
		listActros.add(new UserActor(82297449,
				"3e6e070d985c9c2bfc163a221a02e673f725382db6d54181f0e5e4d11f1bc3445ba089cd4cff0e3d33a32"));
		listActros.add(new UserActor(82297449,
				"199e47ded8d6a7be1ae5242bc3585867a2f9fcffd41214dbeaf7cbb768c05c439015c07ee729c94319ef0"));
		listActros.add(new UserActor(82297449,
				"1b62a6ccd5accf69c4890317a0986dd3d4e17429b9172a7110fe45a627ec1a45a72a0b7cbc21daba53e4c"));

		return listActros;
	}

	private static List<Group> getListGroup(VkApiClient vk, List<UserActor> listActros, int countGroup)
			throws ApiException, ClientException, InterruptedException {
		List<Group> listGroups = new ArrayList<Group>();
		for (int i = 0; i < countGroup; i+=100) {
			for(Group group: vk.groups().search(listActros.get(0), "гродно").count(100).offset(i).execute().getItems()) {
				listGroups.add(group);
			}			
			Thread.sleep(1100);
		}
		return listGroups;

	}

}
