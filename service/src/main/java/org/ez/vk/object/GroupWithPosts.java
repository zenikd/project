package org.ez.vk.object;

import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.wall.WallPostFull;

import java.util.List;

public class GroupWithPosts {
    private Group group;
    private List<WallPostFull> posts;

    public GroupWithPosts(Group group, List<WallPostFull> posts) {
        this.group = group;
        this.posts = posts;
    }

    public Group getGroup() {
        return group;
    }

    public List<WallPostFull> getPosts() {
        return posts;
    }
}
