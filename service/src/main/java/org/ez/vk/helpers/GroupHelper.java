package org.ez.vk.helpers;

import com.vk.api.sdk.objects.groups.Group;

import java.util.List;

public interface GroupHelper {
    List<Integer>getListGroupsByTag(String tag) throws Exception;

    List<Integer> getListGroupsByTag(String tag, int count) throws Exception;

    String getGroupUrl(Group group);
}
