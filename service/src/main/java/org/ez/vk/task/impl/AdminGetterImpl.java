package org.ez.vk.task.impl;

import com.vk.api.sdk.objects.groups.Group;
import org.ez.vk.helpers.impl.model.GroupFilterResult;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;
import org.ez.vk.helpers.impl.model.filter.PostFilter;
import org.ez.vk.task.AdminGetter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminGetterImpl extends RootTask implements AdminGetter {

    @Override
    public void getListAdmins(String tag) {
        try {
            List<Integer> groups = groupHelper.getListGroupIdsFromFile();

            FullGroupFilterCriteria groupFilterCriteria = new FullGroupFilterCriteria();
            groupFilterCriteria.setAddAdminsToResponse(true);

            PostFilter postFilter = new PostFilter();
            postFilter.setDay(30);
            postFilter.setEarlier(true);
            postFilter.setSearchByLastPostDate(true);
            groupFilterCriteria.setPostFilter(postFilter);

            PrintWriter writer = new PrintWriter("adminList.txt", "UTF-8");

            List<GroupFilterResult> result = groupFilter.filterGroup(groupFilterCriteria, groups);
            result
                    .forEach(groupFilterResult -> groupFilterResult.getAdminIds()
                            .forEach(adminId -> writer.println(adminId)));


            writer.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
