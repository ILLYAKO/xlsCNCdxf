package com.illya.model;

public class ItemBar {
    private int itemID;
    private String itemProject;
    private String itemName;
    private int itemQty;
    private String itemProfile;
    private int itemLength;
    private String itemGrade;
    private boolean isNested = false;

    public ItemBar() {}

    public ItemBar(
            String itemProject,
            String itemName,
            int itemQty,
            String itemProfile,
            int itemLength,
            String itemGrade
    ) {
        this.itemProject = itemProject;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemProfile = itemProfile;
        this.itemLength = itemLength;
        this.itemGrade = itemGrade;
    }

    public ItemBar(ItemBar itemCopy) {
        this.itemID = itemCopy.getItemID();
        this.itemProject = itemCopy.getItemProject();
        this.itemName = itemCopy.getItemName();
        this.itemQty = itemCopy.getItemQty();
        this.itemProfile = itemCopy.getItemProfile();
        this.itemLength = itemCopy.getItemLength();
        this.itemGrade = itemCopy.getItemGrade();
        this.isNested = itemCopy.isNested();
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemProject() {
        return itemProject;
    }

    public void setItemProject(String itemProject) {
        this.itemProject = itemProject;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemProfile() {
        return itemProfile;
    }

    public void setItemProfile(String itemProfile) {
        this.itemProfile = itemProfile;
    }

    public int getItemLength() {
        return itemLength;
    }

    public void setItemLength(int itemLength) {
        this.itemLength = itemLength;
    }

    public String getItemGrade() {
        return itemGrade;
    }

    public void setItemGrade(String itemGrade) {
        this.itemGrade = itemGrade;
    }


    public boolean isNested() {
        return isNested;
    }

    public void setNested(boolean nested) {
        isNested = nested;
    }

    @Override
    public String toString() {
        return "ItemPlate{" +
                "itemID=" + itemID +
                ", itemProject='" + itemProject + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQty=" + itemQty +
                ", itemProfile=" + itemProfile +
                ", itemLength=" + itemLength +
                ", itemGrade='" + itemGrade + '\'' +
                ", isNested=" + isNested +
                '}';
    }
}
