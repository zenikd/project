package org.ez.vk.helpers.impl.model.filter;

public class FullGroupFilterCriteria {
    boolean addAdminsToResponse;
    boolean addPostsToResponse;
    boolean addCoverToResponse;

    PostFilter postFilter;

    public boolean isAddAdminsToResponse() {
        return addAdminsToResponse;
    }

    public void setAddAdminsToResponse(boolean addAdminsToResponse) {
        this.addAdminsToResponse = addAdminsToResponse;
    }

    public boolean isAddPostsToResponse() {
        return addPostsToResponse;
    }

    public void setAddPostsToResponse(boolean addPostsToResponse) {
        this.addPostsToResponse = addPostsToResponse;
    }

    public boolean isAddCoverToResponse() {
        return addCoverToResponse;
    }

    public void setAddCoverToResponse(boolean addCoverToResponse) {
        this.addCoverToResponse = addCoverToResponse;
    }

    public PostFilter getPostFilter() {
        return postFilter;
    }

    public void setPostFilter(PostFilter postFilter) {
        this.postFilter = postFilter;
    }
}
