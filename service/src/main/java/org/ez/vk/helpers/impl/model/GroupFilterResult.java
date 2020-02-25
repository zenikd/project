package org.ez.vk.helpers.impl.model;

import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.wall.WallPostFull;

import java.util.List;

public class GroupFilterResult {
    Group group;
    List<Integer> adminIds;
    private Integer groupId;
    private List<WallPostFull> posts;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Integer> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(List<Integer> adminId) {
        this.adminIds = adminId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<WallPostFull> getPosts() {
        return posts;
    }

    public void setPosts(List<WallPostFull> posts) {
        this.posts = posts;
    }
}
