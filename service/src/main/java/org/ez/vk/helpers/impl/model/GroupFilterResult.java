package org.ez.vk.helpers.impl.model;

import com.vk.api.sdk.objects.groups.Group;

import java.util.List;

public class GroupFilterResult {
    Group group;
    List<Integer> adminIds;

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
}
