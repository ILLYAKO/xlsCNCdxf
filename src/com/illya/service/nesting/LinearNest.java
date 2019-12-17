package com.illya.service.nesting;

import com.illya.model.ItemBar;

import java.util.ArrayList;

public class LinearNest {
    int nestLength=0;
    private ArrayList nests = new ArrayList();
    private ArrayList  <ItemBar> nest;
    private int length;
    private boolean letsGo = true;

    public LinearNest(ArrayList items, String textRawL) {
        initNesting(items, textRawL);
    }

    public ArrayList getNests() {
        return nests;
    }

    private void initNesting(ArrayList <ItemBar> items, String textRawL ) {
//       System.out.println("before nest.size " + nests.size());
        length = Integer.parseInt(textRawL);
       ArrayList <ItemBar> reqList = requiredItems(items);
       int j=0;
       nest = new ArrayList<>();
       while (letsGo)
       {
           ItemBar item = reqList.get(j);
           if (allNotNested(reqList)){
               if(!item.isNested()){
                   if(nestLength+item.getItemLength() <= length){   //Shorter
                           item.setNested(true);
//                           System.out.println("item["+j+"] is Nested: " + item.isNested());
                           nest.add(item);
                           nestLength+=item.getItemLength();
                           if(j>=reqList.size()-1)j=0;else j++;
                   }
                   else {
                       if (j<reqList.size()-1){
                           j++;
                       }
                       else{
                           nests.add(nest);
                           nest = new ArrayList<>();
                           nestLength = 0;
                           j=0;
                       }
                   }
               }
               else{
                   if(j>=reqList.size()-1)j=0;else j++;
               }
           }
           else{
               nests.add(nest);
               letsGo=false;
           }
       }
//       System.out.println("After nests.size " + nests.size());

    }

    private boolean allNotNested(ArrayList <ItemBar> reqList){
        int iter;
        for (iter = 0; iter < reqList.size(); iter++) {
            if(!reqList.get(iter).isNested()){
//                System.out.println("Not everything is NESTED yet");
                return true;
            }
        }
//        System.out.println("All items are NESTED");
        return false;
    }

    private ArrayList <ItemBar> requiredItems(ArrayList<ItemBar> items){
        ArrayList requiredItems = new ArrayList();
        items.forEach(item -> {
            for (int i = 0; i < item.getItemQty() ; i++){
                ItemBar tempItem = new ItemBar(item);
                requiredItems.add(tempItem);
            }
        });
        return requiredItems;
    }
}