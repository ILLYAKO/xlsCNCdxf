package com.illya.service.nesting;

import com.illya.model.ItemPlate;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class PlateNest {
//    int nestLength=0;
    int gap ;
    private ArrayList nests = new ArrayList();
    private ArrayList  <ItemPlate> nest = new ArrayList();
    private ArrayList <ItemPlate> reqList;
//    int currentX=0, currentY=0;
    int x=0,y=0;

    private boolean letsDo = true;

    public PlateNest(ArrayList items, ItemPlate rawPlate, int gap ) {
        initNesting(items, rawPlate,gap);
    }

    public ArrayList getNests() {
        return nests;
    }

    private void initNesting(ArrayList <ItemPlate> items, ItemPlate rawPlate, int gap ) {
        this.gap = gap;
        Instant start = Instant.now();
        int rawPlateLength = rawPlate.getItemLength();
        int rawPlateWidth = rawPlate.getItemWidth();

        reqList = requiredItems(items);
        System.out.println("reqList.size(): "+ reqList.size());

        int jItem=0;
//        int maxX=0;
        while (letsDo)
        {
            if(reqList.size()>0){
                ItemPlate item = reqList.get(jItem);
                int temp = 0;
                loopX:for (x=0;x<=rawPlateLength;x++){
                    loopY:for (y=0;y<=rawPlateWidth;y++){
                    if(x +item.getItemLength()<=rawPlateLength){
                        if( y  +item.getItemWidth()<=rawPlateWidth){    // Y
                            if(nest.size()==0){
                                addItemToNest(jItem,x,y,item,nest);
                                reqList.remove(jItem);
                                break loopX;
                            }
                            else{
                                boolean inFreeField=false;
                                for (int k=0; k<nest.size(); k++)
                                {
                                    if(isItemInside(x,y,item,nest.get(k))){
                                        inFreeField = false;
                                        y=y+nest.get(k).getItemWidth();
                                        break;
                                    }
                                    else {
                                        inFreeField = true;
                                    }
                                }
                                if(inFreeField)
                                {
                                    addItemToNest(jItem,x,y,item,nest);
                                    reqList.remove(jItem);
                                    jItem=0;
                                    break loopX;
                                }
                            }
                        }
                        else{
//                            break loopY;
                        }
                    }
                    else{
                        if(jItem<reqList.size()-1){
                            jItem++;// check loop!!!
                            break loopX;
                            }
                        else{
                            nests.add(nest);
                            nest = new ArrayList();
                            jItem=0;
                            break loopX;
                        }
                    }
                }
                }
            }else{
                nests.add(nest);
                letsDo = false;
                System.out.println("DONE!!!");
            }
        }
        System.out.println("After nests.size " + nests.size());
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
    }
//    private boolean isSomeFreeItem(ArrayList <ItemPlate> reqList){
//        int iter;
//        for (iter = 0; iter < reqList.size(); iter++) {
//            if(!reqList.get(iter).isNested()){
//                return true;
//            }
//        }
//        return false;
//    }

    private ArrayList <ItemPlate> requiredItems(ArrayList<ItemPlate> items){
        ArrayList requiredItems = new ArrayList();
        items.forEach(item -> {
            for (int i = 0; i < item.getItemQty() ; i++){
                ItemPlate tempItem = new ItemPlate(item);
                tempItem.setItemWidth(tempItem.getItemWidth()+gap);     // plus gap
                tempItem.setItemLength(tempItem.getItemLength()+gap);   // plus gap
                requiredItems.add(tempItem);
            }
        });
        return requiredItems;
    }

    private void addItemToNest(int j,int x, int y, ItemPlate item, ArrayList  <ItemPlate> nest){
        item.setNested(true);
        item.setX(x);
        item.setY(y);
        nest.add(item);

        System.out.println(
                "Column: "+x+"; Raw: " + y+ "; reqList.get("+j+"): "
                        + " Item Name: "+item.getItemName()
                        +", nest.size(): " + nest.size());
    }

    private boolean isItemInside(int x, int y,  ItemPlate item, ItemPlate itemNested){
        if(x > itemNested.getX()+itemNested.getItemLength() || itemNested.getX() > x+item.getItemLength()) return false;
        if(y+item.getItemWidth() < itemNested.getY() || itemNested.getY()+itemNested.getItemWidth() < y) return false;
        return true;
    }

//    private boolean isInsideRawMaterial(int x, int y,  ItemPlate item, ItemPlate itemRawMaterial){
//        return((   x >= itemRawMaterial.getX() && x <= itemRawMaterial.getX() + itemRawMaterial.getItemLength()
//                && y >= itemRawMaterial.getY() && y <= itemRawMaterial.getY() + itemRawMaterial.getItemWidth())
//                &&
//                (  x + item.getItemLength() >= itemRawMaterial.getX() && x + item.getItemLength() <= itemRawMaterial.getX() + itemRawMaterial.getItemLength()
//                        && y >= itemRawMaterial.getY() && y <= itemRawMaterial.getY() + itemRawMaterial.getItemWidth())
//                &&
//                (  x + item.getItemLength() >= itemRawMaterial.getX() && x + item.getItemLength() <= itemRawMaterial.getX() + itemRawMaterial.getItemLength()
//                        && y + item.getItemWidth() >= itemRawMaterial.getY() && y + item.getItemWidth() <= itemRawMaterial.getY() + itemRawMaterial.getItemWidth())
//                &&
//                (  x >= itemRawMaterial.getX() && x <= itemRawMaterial.getX() + itemRawMaterial.getItemLength()
//                        && y + item.getItemWidth() >= itemRawMaterial.getY() && y + item.getItemWidth() <= itemRawMaterial.getY() + itemRawMaterial.getItemWidth())
//        );
//    }
}