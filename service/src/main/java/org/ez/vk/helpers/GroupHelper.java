package org.ez.vk.helpers;

import com.vk.api.sdk.objects.groups.Group;
import org.ez.vk.exception.internal.InternalException;

import java.util.List;

public interface GroupHelper {
    List<Group> getListGroupsByTag(String tag) throws Exception;

    String getGroupUrl(Group group);
}
