package org.ez.vk.helpers.impl.model.filter;

public class PostFilter extends BaseFilter {
    boolean searchByLastPostDate;
    boolean isEarlier;
    int day;
    int minAmountPosts;

    int minAverageLikes;
    int minAverageReposts;
    int minAverageComments;


    public boolean isSearchByLastPostDate() {
        return searchByLastPostDate;
    }

    public void setSearchByLastPostDate(boolean searchByLastPostDate) {
        this.searchByLastPostDate = searchByLastPostDate;
    }

    public boolean isEarlier() {
        return isEarlier;
    }

    public void setEarlier(boolean earlier) {
        isEarlier = earlier;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int month) {
        this.day = month;
    }

    public int getMinAmountPosts() {
        return minAmountPosts;
    }

    public void setMinAmountPosts(int minAmountPosts) {
        this.minAmountPosts = minAmountPosts;
    }

    public int getMinAverageLikes() {
        return minAverageLikes;
    }

    public void setMinAverageLikes(int minAverageLikes) {
        this.minAverageLikes = minAverageLikes;
    }

    public int getMinAverageReposts() {
        return minAverageReposts;
    }

    public void setMinAverageReposts(int minAverageReposts) {
        this.minAverageReposts = minAverageReposts;
    }

    public int getMinAverageComments() {
        return minAverageComments;
    }

    public void setMinAverageComments(int minAverageComments) {
        this.minAverageComments = minAverageComments;
    }
}
