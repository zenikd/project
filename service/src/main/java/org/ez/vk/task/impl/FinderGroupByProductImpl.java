package org.ez.vk.task.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ez.vk.task.FinderGroupByProduct;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.market.MarketItem;
import com.vk.api.sdk.objects.market.MarketItemFull;
import com.vk.api.sdk.objects.wall.WallPostFull;
import com.vk.api.sdk.queries.groups.GroupField;

@Service
public class FinderGroupByProductImpl extends BaseTask implements FinderGroupByProduct {
    private final static Integer COUNT_GROUP = 100;
    private final static Integer COUNT_ACCOUNT = 1;


    public void getListGroup(String tag) {


        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Birthdays");

        try {
            //List<Group> listGroups = this.groupHelper.getListGroupsByTag(tag);
            List<Group> listGroups =  new ArrayList<>();
            UserActor userActor = this.accountService.getAccountsByType(COUNT_ACCOUNT).get(0).getUserActor();

            int numberRow = 0;
            int contractLowGroup = 0;

            for (int number = 0; number < listGroups.size(); number++) {
                Group group = listGroups.get(number);


                try {
                    Integer groupId = -listGroups.get(number).getId();

                    Thread.sleep(1100);
                    int membersCount = vk.groups().getById(userActor).fields(GroupField.MEMBERS_COUNT).groupId(group.getId().toString()).execute().get(0).getMembersCount();

                    if (membersCount < 500) {
                        contractLowGroup++;

                        if (contractLowGroup > 10) {
                            System.out.println("low group");
                            break;
                        }

                        continue;
                    }

                    contractLowGroup = 0;

                    Thread.sleep(1100);
                    List<MarketItem> items = vk.market().get(userActor, groupId).execute().getItems();


                    int productViewsCount = -1;
                    float productViewsMark = -1;

                    if (items.size() != 0) {


                        MarketItem item = items.get(0);
                        MarketItemFull result = vk.market().getByIdExtended(userActor, groupId + "_" + item.getId()).execute().getItems().get(0);

                        long creationDate = result.getDate();
                        long currenDate = new Date().getTime() / 1000;
                        Long time = currenDate - creationDate;
                        float days = (float) TimeUnit.DAYS.convert(time, TimeUnit.SECONDS);

                        productViewsCount = result.getViewsCount();
                        productViewsMark = (float) result.getViewsCount() / days;

                    }

                    Row row = sheet.createRow(numberRow);

                    Cell url = row.createCell(0);
                    url.setCellValue(this.groupHelper.getGroupUrl(groupId));

                    Cell name = row.createCell(1);
                    name.setCellValue(group.getName());

                    Cell tagCell = row.createCell(2);
                    tagCell.setCellValue(tag);

                    Cell membersCountCell = row.createCell(3);
                    membersCountCell.setCellValue(membersCount);

                    Cell productViewsCountCell = row.createCell(4);
                    productViewsCountCell.setCellValue(productViewsCount);

                    Cell productViewsMarkCell = row.createCell(5);
                    productViewsMarkCell.setCellValue(productViewsMark);


                    Thread.sleep(1100);
                    List<WallPostFull> wallPostFull = vk.wall().get(userActor).count(100).ownerId(groupId).execute().getItems();


                    int countPostWithView = wallPostFull.stream().filter(post -> post.getViews() != null).map(post -> post.getViews().getCount()).reduce(0, (sum, val) -> sum + 1);

                    Cell countPostViewCell = row.createCell(6);
                    countPostViewCell.setCellValue(wallPostFull.stream().filter(post -> post.getViews() != null).map(post -> (post.getViews() != null ? post.getViews().getCount() : 0)).reduce(0, (sum, val) -> sum + val));

                    Cell countLikesViewCell = row.createCell(7);
                    countLikesViewCell.setCellValue(wallPostFull.stream().filter(post -> post.getViews() != null).map(post -> post.getLikes().getCount()).reduce(0, (sum, val) -> sum + val));

                    Cell countRepostsViewCell = row.createCell(8);
                    countRepostsViewCell.setCellValue(wallPostFull.stream().filter(post -> post.getViews() != null).map(post -> post.getReposts().getCount()).reduce(0, (sum, val) -> sum + val));

                    Cell countPosts = row.createCell(9);
                    countPosts.setCellValue(countPostWithView);


                    Cell birthdate = row.createCell(10);
                    DataFormat format = book.createDataFormat();
                    CellStyle dateStyle = book.createCellStyle();
                    dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
                    birthdate.setCellStyle(dateStyle);
                    birthdate.setCellValue(new Date());

                    numberRow++;
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }

        } catch (Exception e1) {
            System.out.println(e1);
        }

        try {
            FileOutputStream file = new FileOutputStream("listGroup.xlsx");
            book.write(file);
            book.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }


    }
}
