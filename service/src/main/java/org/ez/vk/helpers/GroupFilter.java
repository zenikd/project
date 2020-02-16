package org.ez.vk.helpers;

import com.vk.api.sdk.objects.groups.Group;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;

import java.util.List;

public interface GroupFilter {
   List<GroupFilterResult> filterGroup(FullGroupFilterCriteria groupFilterCriteria, List<Integer> groups) throws InternalException;
}

