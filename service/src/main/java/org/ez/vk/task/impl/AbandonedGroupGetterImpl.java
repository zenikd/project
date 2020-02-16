package org.ez.vk.task.impl;

import com.vk.api.sdk.objects.groups.Group;
import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.filter.PostFilter;
import org.ez.vk.task.AbandonedGroupGetter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbandonedGroupGetterImpl extends RootTask implements AbandonedGroupGetter {

    @Override
    public void getListGroup(String tag) {

        try {
            List<Integer> groups = groupHelper.getListGroupsByTag(tag);

            FullGroupFilterCriteria groupFilterCriteria = new FullGroupFilterCriteria();

            PostFilter postFilter = new PostFilter();
            postFilter.setDay(365);
            postFilter.setEarlier(false);
            postFilter.setSearchByLastPostDate(true);
            groupFilterCriteria.setPostFilter(postFilter);

            List<GroupFilterResult> result = groupFilter.filterGroup(groupFilterCriteria, groups);
            result
                    .forEach(groupFilterResult -> System.out.println(groupHelper.getGroupUrl(groupFilterResult.getGroup())));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
