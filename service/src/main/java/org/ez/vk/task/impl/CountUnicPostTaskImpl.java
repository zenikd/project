package org.ez.vk.task.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonElement;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

@Service
public class CountUnicPostTaskImpl extends RootTask {
    private final static Integer COUNT_ACCOUNT = 10;

    @Autowired
    DateHelper dateHelper;

    public Integer consider() throws InternalException {
        List<AccountVk> listAccount = this.accountService.getAccountsByType(COUNT_ACCOUNT);
        UserActor actor = listAccount.get(0).getUserActor();
        return getCount(actor);
    }

    private Integer getCount(UserActor actor) throws InternalException {
        try {
            JsonElement response = vk.execute().code(actor, "return API.groups.search({\"offset\": 990, \"q\": \"клиника\"});")
                    .execute();
            return 1;
        } catch (Exception e) {
            throw new InternalException();
        }

    }

    private void setUnicePost(GetResponse posts, Long datePost, Set<Integer> postAuthor) {
        for (WallPostFull post : posts.getItems()) {
            if (!postAuthor.contains(post.getFromId())) {
                postAuthor.add(post.getFromId());
                datePost = Long.valueOf(post.getDate().toString());
            }
        }
    }
}
