package org.ez.vk.helpers.impl.model.filter;

public class BaseFilter {
    boolean addToResponse;
    boolean isImportant;

    public boolean isAddToResponse() {
        return addToResponse;
    }

    public void setAddToResponse(boolean addToResponse) {
        this.addToResponse = addToResponse;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
