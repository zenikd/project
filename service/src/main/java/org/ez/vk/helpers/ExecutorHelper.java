package org.ez.vk.helpers;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.ez.vk.helpers.impl.model.GroupIdWithPosts;

import java.util.List;

public interface ExecutorHelper {
    public List<GroupIdWithPosts> getGroupPosts(List<Integer> groupIds, UserActor actor) throws ClientException, ApiException;
}
