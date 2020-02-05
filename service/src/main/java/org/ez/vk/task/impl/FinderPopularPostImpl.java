package org.ez.vk.task.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.task.FinderPopularPost;
import org.springframework.stereotype.Service;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupType;
import com.vk.api.sdk.objects.wall.WallPostFull;

@Service
public class FinderPopularPostImpl extends RootTask implements FinderPopularPost {
    private final static Integer COUNT_ACCOUNT = 1;


    public void getListPost(String tag) {
        try {
            List<Group> listGroups = this.groupHelper.getListGroupsByTag(tag);
            UserActor userActor = this.accountService.getAccountsByType(COUNT_ACCOUNT).get(0).getUserActor();

            List<PostWithValue> list = new ArrayList();
            for (int i = 0; i < 100; i++) {
                list.add(new PostWithValue());
            }

            for (int number = 0; number < listGroups.size(); number++) {
                try {

                    Group group = listGroups.get(number);

                    if (group.getType() == GroupType.GROUP) continue;

                    int groupId = -group.getId();
                    Thread.sleep(1100);
                    List<WallPostFull> wallPostFull = vk.wall().get(userActor).count(100).ownerId(groupId).execute().getItems();

                    if (wallPostFull.size() < 10) continue;

                    List<WallPostFull> postsWithoutPinned = wallPostFull.stream().filter(post -> post != null && post.getIsPinned() == null).collect(Collectors.toList());

                    int postCount = postsWithoutPinned.size();

                    float likesCount = (float) postsWithoutPinned.stream().map(post -> post.getLikes().getCount()).reduce(0, (sum, value) -> sum + value);
                    if (likesCount < 100) continue;

                    float avg = likesCount / postCount;

                    for (WallPostFull post : postsWithoutPinned) {

                        float value = post.getLikes().getCount() / avg;
                        if (list.get(99).value < value) {
                            PostWithValue postWithValue = new PostWithValue();
                            postWithValue.value = value;
                            postWithValue.url = this.groupHelper.getGroupUrl(group);
                            list.set(99, postWithValue);
                            Collections.sort(list, (d1, d2) -> {
                                return (int) (d2.value * 100 - d1.value * 100);
                            });
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }


            }

            list.stream().forEach(post -> System.out.println(post.url));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}


class PostWithValue {
    float value;
    String url;
}
