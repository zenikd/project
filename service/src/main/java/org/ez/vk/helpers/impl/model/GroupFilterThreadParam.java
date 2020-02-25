package org.ez.vk.helpers.impl.model;

import com.vk.api.sdk.client.actors.UserActor;
import org.ez.vk.helpers.ExecutorHelper;
import org.ez.vk.helpers.impl.model.filter.FullGroupFilterCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GroupFilterThreadParam {
    FullGroupFilterCriteria groupFilterCriteria;
    List<Integer> groupsIds;
    UserActor userActor;
    ExecutorHelper executorHelper;

    AtomicInteger groupIndexToPost;
    AtomicInteger groupIndexToAdmin;

    CopyOnWriteArrayList<GroupFilterResult> groupFilterResults;

    public FullGroupFilterCriteria getGroupFilterCriteria() {
        return groupFilterCriteria;
    }

    public List<Integer> getGroupsIds() {
        return groupsIds;
    }

    public UserActor getUserActor() {
        return userActor;
    }

    public AtomicInteger getGroupIndexToPost() {
        return groupIndexToPost;
    }

    public AtomicInteger getGroupIndexToAdmin() {
        return groupIndexToAdmin;
    }

    public CopyOnWriteArrayList<GroupFilterResult> getGroupFilterResults() {
        return groupFilterResults;
    }

    public ExecutorHelper getExecutorHelper() {
        return executorHelper;
    }

    public void setGroupFilterCriteria(FullGroupFilterCriteria groupFilterCriteria) {
        this.groupFilterCriteria = groupFilterCriteria;
    }

    public void setGroupsIds(List<Integer> groupsIds) {
        this.groupsIds = groupsIds;
    }

    public void setUserActor(UserActor userActor) {
        this.userActor = userActor;
    }

    public void setExecutorHelper(ExecutorHelper executorHelper) {
        this.executorHelper = executorHelper;
    }

    public void setGroupIndexToPost(AtomicInteger groupIndexToPost) {
        this.groupIndexToPost = groupIndexToPost;
    }

    public void setGroupIndexToAdmin(AtomicInteger groupIndexToAdmin) {
        this.groupIndexToAdmin = groupIndexToAdmin;
    }

    public void setGroupFilterResults(CopyOnWriteArrayList<GroupFilterResult> groupFilterResults) {
        this.groupFilterResults = groupFilterResults;
    }
}
