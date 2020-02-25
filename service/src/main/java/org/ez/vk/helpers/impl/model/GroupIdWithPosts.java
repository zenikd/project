package org.ez.vk.helpers.impl.model;

import com.vk.api.sdk.objects.wall.WallPostFull;

import java.util.List;

public class GroupIdWithPosts {
    private Integer groupId;
    private List<WallPostFull> posts;

    public GroupIdWithPosts(Integer groupId, List<WallPostFull> list) {
        this.groupId = groupId;
        this.posts = list;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public List<WallPostFull> getPosts() {
        return posts;
    }
}
