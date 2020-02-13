package org.ez.vk.task.impl;

import com.vk.api.sdk.objects.groups.Group;
import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.filter.PostFilter;
import org.ez.vk.task.AdminGetter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminGetterImpl extends RootTask implements AdminGetter {

    @Override
    public void getListAdmins(String tag) {

        try {
            List<Group> groups = groupHelper.getListGroupsByTag(tag);

            FullGroupFilterCriteria groupFilterCriteria = new FullGroupFilterCriteria();
            groupFilterCriteria.setAddAdminsToResponse(true);

            PostFilter postFilter = new PostFilter();
            postFilter.setDay(1);
            postFilter.setEarlier(true);
            postFilter.setSearchByLastPostDate(true);
            groupFilterCriteria.setPostFilter(postFilter);

            List<GroupFilterResult> result = groupFilter.filterGroup(groupFilterCriteria, groups);
            result
                    .forEach(groupFilterResult -> groupFilterResult.getAdminIds()
                            .forEach(adminId -> System.out.println(groupHelper.getGroupUrl(groupFilterResult.getGroup()) + " " + adminId)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
