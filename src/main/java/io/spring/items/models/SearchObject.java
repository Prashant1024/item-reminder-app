package io.spring.items.models;

public class SearchObject {

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public SearchObject() {
    }

    public SearchObject(String itemName) {
        this.itemName = itemName;
    }
}
