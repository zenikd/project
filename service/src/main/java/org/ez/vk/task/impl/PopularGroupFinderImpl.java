package org.ez.vk.task.impl;

import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.filter.PostFilter;
import org.ez.vk.task.PopularGroupFinder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopularGroupFinderImpl extends BaseTask implements PopularGroupFinder {

    @Override
    public void getPopularGroup(String tag) {
        try {
            List<String> incompatibleTags = new ArrayList<>();
            incompatibleTags.add("интер");
            incompatibleTags.add("декор");
            incompatibleTags.add("ногт");

            List<Integer> groups = groupHelper.getListGroupsByTag(tag, 1000, incompatibleTags);

            FullGroupFilterCriteria groupFilterCriteria = new FullGroupFilterCriteria();
            groupFilterCriteria.setAddAdminsToResponse(true);

            PostFilter postFilter = new PostFilter();
            postFilter.setMinAverageLikes(10);
            postFilter.setMinAmountPosts(30);

            postFilter.setDay(30);
            postFilter.setEarlier(true);
            postFilter.setSearchByLastPostDate(true);

            groupFilterCriteria.setPostFilter(postFilter);

            groupFilterCriteria.setPostFilter(postFilter);

            PrintWriter writer = new PrintWriter("popularGroupsIds.txt", "UTF-8");

            List<GroupFilterResult> result = groupFilter.filterGroup(groupFilterCriteria, groups);
            result
                    .forEach(groupFilterResult -> writer.println(groupFilterResult.getGroupId()));
            writer.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
