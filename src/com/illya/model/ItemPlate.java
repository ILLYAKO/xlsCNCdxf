package com.illya.model;

public class ItemPlate {
    private int itemID;
    private String itemProject;
    private String itemName;
    private int itemQty;
    private float itemThickness;
    private int itemWidth;
    private int itemLength;
    private String itemGrade;
    private boolean itemDOR = false;
    private boolean isNested = false;
    private int x;
    private int y;

    public ItemPlate() {
    }

    public ItemPlate(
            String itemProject,
            String itemName,
            int itemQty,
            float itemThickness,
            int itemWidth,
            int itemLength,
            String itemGrade
    ) {
        this.itemProject = itemProject;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemThickness = itemThickness;
        this.itemLength = itemLength;
        this.itemWidth = itemWidth;
        this.itemGrade = itemGrade;
    }

    public ItemPlate(
            String itemProject,
            String itemName,
            int itemQty,
            float itemThickness,
            int itemWidth,
            int itemLength,
            String itemGrade,
            boolean itemDOR
    ) {
        this.itemProject = itemProject;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemThickness = itemThickness;
        this.itemLength = itemLength;
        this.itemWidth = itemWidth;
        this.itemGrade = itemGrade;
        this.itemDOR = itemDOR;
    }

    public ItemPlate(ItemPlate itemCopy) {
        this.itemID = itemCopy.getItemID();
        this.itemProject = itemCopy.getItemProject();
        this.itemName = itemCopy.getItemName();
        this.itemQty = itemCopy.getItemQty();
        this.itemThickness = itemCopy.getItemThickness();
        this.itemWidth = itemCopy.getItemWidth();
        this.itemLength = itemCopy.getItemLength();
        this.itemGrade = itemCopy.getItemGrade();
        this.itemDOR = itemCopy.isItemDOR();
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

    public float getItemThickness() {
        return itemThickness;
    }

    public void setItemThickness(float itemThickness) {
        this.itemThickness = itemThickness;
    }

    public int getItemLength() {
        return itemLength;
    }

    public void setItemLength(int itemLength) {
        this.itemLength = itemLength;
    }

    public int getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    public String getItemGrade() {
        return itemGrade;
    }

    public void setItemGrade(String itemGrade) {
        this.itemGrade = itemGrade;
    }

    public boolean isItemDOR() {
        return itemDOR;
    }

    public void setItemDOR(boolean itemDOR) {
        this.itemDOR = itemDOR;
    }

    public boolean isNested() {
        return isNested;
    }

    public void setNested(boolean nested) {
        isNested = nested;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "ItemPlate{" +
                "itemID=" + itemID +
                ", itemProject='" + itemProject + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQty=" + itemQty +
                ", itemThickness=" + itemThickness +
                ", itemWidth=" + itemWidth +
                ", itemLength=" + itemLength +
                ", itemGrade='" + itemGrade + '\'' +
                ", itemDOR=" + itemDOR +
                ", isNested=" + isNested +
                '}';
    }
}
