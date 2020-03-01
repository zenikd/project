package org.ez.vk.helpers;

import com.vk.api.sdk.objects.groups.Group;

import java.io.IOException;
import java.util.List;

public interface GroupHelper {
    List<Integer> getListGroupsByTag(String tag) throws Exception;

    List<Integer> getListGroupsByTag(String tag, int count, List<String> incompatibleTags) throws Exception;

    public List<Integer> getListGroupIdsFromFile() throws IOException;

    String getGroupUrl(int groupId);
}
