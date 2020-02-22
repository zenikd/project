package org.ez.vk.helpers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallPostFull;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helpers.ExecutorHelper;
import org.ez.vk.helpers.impl.model.GroupIdWithPosts;
import org.ez.vk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecutorHelperImpl implements ExecutorHelper {
    protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

    @Autowired
    protected AccountService accountService;

    public List<GroupIdWithPosts> getGroupPosts(List<Integer> groupIds, UserActor actor) throws ClientException, ApiException {
        String query = "var groupIds = " + Arrays.toString(groupIds.toArray()) + ";" +
                "var response = [];" +
                "var index = 0;" +
                "while(index < groupIds.length) {" +
                    "var groupId = groupIds[index];" +
                    "var posts = API.wall.get({\"count\": 100, \"owner_id\": -groupId});" +
                    "response.push({groupId: groupId, posts: posts});" +
                    "index = index + 1;" +
                "};" +
                "return response;";


        JsonElement jsonResult = vk.execute().code(actor, query)
                .execute();
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();

        List<GroupIdWithPosts> response = new ArrayList<>();

        jsonResult.getAsJsonArray().forEach(jsonElement -> {
            String mJsonString = jsonElement.toString();

            JsonArray itemsArray = jsonElement.getAsJsonObject().get("posts").getAsJsonObject().get("items").getAsJsonArray();
            List<WallPostFull> posts = new ArrayList<>();
            itemsArray.forEach(postJson -> {
                try {
                    postJson.getAsJsonObject().remove("type");
                    postJson.getAsJsonObject().remove("marked_as_ads");
                    postJson.getAsJsonObject().remove("post_type");
                    postJson.getAsJsonObject().remove("attachments");
                    postJson.getAsJsonObject().remove("post_source");
                    postJson.getAsJsonObject().get("comments").getAsJsonObject().remove("groups_can_post");

                    String correctJsonString = converToCorrectJsonString(postJson.toString());
                    WallPostFull post = mapper.readValue(correctJsonString, WallPostFull.class);

                    posts.add(post);
                } catch (IOException e) {

                }
            });
            Integer groupId = jsonElement.getAsJsonObject().get("groupId").getAsInt();

            GroupIdWithPosts groupIdWithPosts = new GroupIdWithPosts(groupId, posts);
            response.add(groupIdWithPosts);
        });

        return response;
    }

    private String converToCorrectJsonString(String inccorectJson) {
        String[] removedUnderScoreArray = inccorectJson.split("_");
        List<String> removedUnderScoreList = Arrays.asList(removedUnderScoreArray);
        return removedUnderScoreList.stream().reduce("", (sum, res) -> {
            return sum + res.substring(0, 1).toUpperCase() + res.substring(1);
        });
    }

}
