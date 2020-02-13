package org.ez.vk.helpers.impl.model.filter;

public class PostFilter extends BaseFilter {
    boolean searchByLastPostDate;
    boolean isEarlier;
    int day;

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
}
