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

        List<Integer> groups = new ArrayList<>();


        try {
            //List<Integer> groups = groupHelper.getListGroupsByTag(tag, 1000);

            Path pathToFile = Paths.get("12.txt");

            // create an instance of BufferedReader
            // using try with resource, Java 7 feature to close resources
            try (BufferedReader br = Files.newBufferedReader(pathToFile,
                    StandardCharsets.US_ASCII)) {

                // read the first line from the text file
                String line = br.readLine();

                // loop until all lines are read
                while (line != null) {
                    groups.add(Integer.parseInt(line));
                    line = br.readLine();
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }




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
