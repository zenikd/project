package org.ez.vk.task.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.task.FinderPopularPost;
import org.springframework.stereotype.Service;
import com.vk.api.sdk.objects.wall.WallPostFull;

@Service
public class FinderPopularPostImpl extends BaseTask implements FinderPopularPost {
    private final static Integer COUNT_ACCOUNT = 1;


    public void getListPost(String tag) {
        try {
           // List<Group> listGroups = this.groupHelper.getListGroupsByTag(tag);
            List<Integer> groupsIds = groupService.getGroupsIdsByTag("design");

            List<GroupFilterResult> groupFilterResultList = groupFilter.filterGroup(new FullGroupFilterCriteria(), groupsIds);

            List<PostWithValue> list = new ArrayList();
            for (int i = 0; i < 100; i++) {
                list.add(new PostWithValue());
            }
            groupFilterResultList.forEach(groupFilterResult -> {
                try {

                    List<WallPostFull> wallPostFull =groupFilterResult.getPosts();

                    if (wallPostFull.size() < 10) return;

                    List<WallPostFull> postsWithoutPinned = wallPostFull.stream().filter(post -> post != null && post.getIsPinned() == null).collect(Collectors.toList());

                    int postCount = postsWithoutPinned.size();

                    float likesCount = (float) postsWithoutPinned.stream().map(post -> post.getLikes().getCount()).reduce(0, (sum, value) -> sum + value);
                    if (likesCount < 100) return;

                    float avg = likesCount / postCount;

                    for (WallPostFull post : postsWithoutPinned) {

                        float value = post.getLikes().getCount() / avg;
                        if (list.get(99).value < value) {
                            PostWithValue postWithValue = new PostWithValue();
                            postWithValue.value = value;
                            postWithValue.url = this.groupHelper.getGroupUrl(groupFilterResult.getGroupId()) + "?w=wall-" + groupFilterResult.getGroupId() + "_" + post.getId() ;
                            list.set(99, postWithValue);
                            Collections.sort(list, (d1, d2) -> {
                                return (int) (d2.value * 100 - d1.value * 100);
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            list.stream().forEach(post -> System.out.println(post.url));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class PostWithValue {
    float value;
    String url;
}
