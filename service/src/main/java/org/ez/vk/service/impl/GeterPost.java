package org.ez.vk.service.impl;

import java.util.Date;
import java.util.List;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

public class GeterPost extends Thread {
	List<String> listPost;
	int i;
	Group group;
	VkApiClient vk;
	UserActor actor;

	GeterPost(List<String> listPost, int i, Group group, VkApiClient vk, UserActor actor) {
		this.listPost = listPost;
		this.i = i;
		this.group = group;
		this.vk = vk;
		this.actor = actor;
	}

	public void run() {
		try {
			GetResponse getResponse = vk.wall().get(actor).ownerId(-group.getId()).count(100).offset(0).execute();
			for (int a = 0; a < 100; a++) {
				if (getResponse.getItems().get(a).getText().contains("репост") && getResponse.getItems().get(a).getDate() +2629743 >new Date().getTime()/1000) {
					String type;
					if (group.getType().equals("GROUP")) {
						type = "club";
					} else {
						type = "public";
					}
					String linkPost = String.format("https://vk.com/%s%s?w=wall-%s_%s", type, group.getId(),
							group.getId(), getResponse.getItems().get(a).getId());

					listPost.add(linkPost);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
