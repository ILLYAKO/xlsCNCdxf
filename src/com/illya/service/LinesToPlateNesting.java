package com.illya.service;

import com.illya.model.ItemPlate;
import com.illya.service.nesting.PlateNest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LinesToPlateNesting {
    private ArrayList items = new ArrayList();
    private ItemPlate item;
    private String fileName = "";
    private ItemPlate rawPlate;
    private int gap, gapOriginal;
    private float areaTotal = 0.0f;

    public LinesToPlateNesting(ArrayList arrayLines, String textRawL, String textGap) {
        this.gapOriginal = Integer.parseInt(textGap);
//        System.out.println("--LinesToPlateNesting--");
        arrayLines.forEach(line->{
            String[] names = line.toString().split(",");
            item = new ItemPlate(
                            names[0],
                            names[1],
                            Integer.parseInt(names[2]),
                            Float.parseFloat(names[3]),
                            Integer.parseInt(names[4]),
                            Integer.parseInt(names[5]),
                            names[6]
            );
//            System.out.println("--ITEM: "+item);
            items.add(item);
        });
//        System.out.println(items);

        rawPlate = new ItemPlate(item.getItemProject(),
                "Raw Material",
                1,
                0.0f,                                  // <<<<< must change
                Integer.parseInt(textRawL.split("x|X")[1]),
                Integer.parseInt(textRawL.split("x|X")[0]),
                item.getItemGrade()
                );
        rawPlate.setX(0);
        rawPlate.setY(0);

        areaTotal = rawPlate.getItemLength()*rawPlate.getItemWidth();//mm^2

        PlateNest nests = new PlateNest(items, rawPlate, gapOriginal); // <<<<<<<<<<<<<<<<<<<----------------------------

        try {
            body(nests,rawPlate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void body (PlateNest nests, ItemPlate rawPlate) throws IOException {
        ArrayList tempNests = nests.getNests();
        FileWriter fw = null;

        for (int i = 0; i < tempNests.size(); i++) {
            fileName = "PlateNest-"+(i+1);
            fw = new FileWriter(fileName+".dxf");
            drawText(fw,"File: "+ fileName +".dxf","0", "-10");
            gap=0;
            drawRectangle(fw, rawPlate );
            gap=gapOriginal;
            ArrayList <ItemPlate> tempNest = (ArrayList) tempNests.get(i);
            // <<<<<<<<<<<<<<<<<<<----------------------------
            drawText(fw,"Total Area: "+((float)rawPlate.getItemLength()*rawPlate.getItemWidth())/1000000+"m2","0", "-20");
            drawText(fw,
                    "Used Area: "+areaUsed(tempNest)/1000000+"m2. Waste: "+
                            (1 - areaUsed(tempNest)/(float)(rawPlate.getItemLength()*rawPlate.getItemWidth()))*100+"%",
                    "0", "-30");
            itemCounter(fw,tempNest);

                for(int f=0; f<tempNest.size();f++){
                    try {
                        drawRectangle(fw, tempNest.get(f) );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            fw.write("  0\n");            //'EOF marking the end of the file
            fw.write("EOF\n");
            fw.close();
        }
    }

     float areaUsed(ArrayList <ItemPlate> itemPlateList){
            float areaUsed = 0.0f;
             for (ItemPlate item : itemPlateList) {
                 areaUsed =areaUsed + (item.getItemLength()-gapOriginal)*(item.getItemWidth()-gapOriginal);
             }
             return areaUsed;
         }

     void itemCounter(FileWriter fw,ArrayList <ItemPlate> itemPlateList) throws IOException {
             Map<String, Integer> duplicates = new HashMap<String, Integer>();
             for (ItemPlate item : itemPlateList) {
                 if (duplicates.containsKey(item.getItemName())) {
                     duplicates.put(item.getItemName(), duplicates.get(item.getItemName()) + 1);
                 } else {
                     duplicates.put(item.getItemName(), 1);
                 }
             }
             drawText(fw,"Result:","0", "-40");
             int l = 50;
             for (Map.Entry<String, Integer> entry : duplicates.entrySet()) {
                 drawText(fw,(entry.getKey() + " = " + entry.getValue()),"30", ("-"+l));
                 l+=10;
             }
         }

     void drawRectangle(FileWriter fw, ItemPlate plItem) throws IOException {
            fw.write("  0\n");          //'Each section starts with group code 0
            fw.write("SECTION\n");
            fw.write("  2\n");          //'Then, there's group code 2 ,followed by the name of the section
            fw.write("ENTITIES\n");
            fw.write("  0\n");          //'Group of codes and value that define its element
            fw.write("POLYLINE\n");
            fw.write("  8\n");            //Layer
            fw.write("0\n");
            fw.write(" 66\n");            //'Entities follow flag
            fw.write("     1\n");
            fw.write(" 10\n");            //X-coordinate
            fw.write("0.0\n");
            fw.write(" 20\n");            //Y-coordinate
            fw.write("0.0\n");
            fw.write(" 70\n");            //'Polyline flag 1 = This is a closed polyline
            fw.write("     1\n");         //'1 = This is a closed polyline
            //-1
            fw.write("  0\n");            //'vertex entities - pick of object
            fw.write("VERTEX\n");
            fw.write("  8\n");            //Layer
            fw.write("0\n");
            fw.write(" 10\n");            //X-coordinate
            fw.write((plItem.getX()+gap/2)+"\n");
            fw.write(" 20\n");            //Y-coordinate
            fw.write((plItem.getY()+gap/2)+"\n");
            //-2
            fw.write("  0\n");            //'vertex entities - pick of object
            fw.write("VERTEX\n");
            fw.write("  8\n");            //Layer
            fw.write("0\n");
            fw.write(" 10\n");            //X-coordinate
            fw.write((plItem.getX()+plItem.getItemLength()-gap/2)+"\n");
            fw.write(" 20\n");            //Y-coordinate
            fw.write((plItem.getY()+gap/2)+"\n");
            //-3
            fw.write("  0\n");            //'vertex entities - pick of object
            fw.write("VERTEX\n");
            fw.write("  8\n");            //Layer
            fw.write("0\n");
            fw.write(" 10\n");            //X-coordinate
            fw.write((plItem.getX()+plItem.getItemLength()-gap/2)+"\n");
            fw.write(" 20\n");            //Y-coordinate
            fw.write((plItem.getY()+plItem.getItemWidth()-gap/2)+"\n");
            //-4
            fw.write("  0\n");            //'vertex entities - pick of object
            fw.write("VERTEX\n");
            fw.write("  8\n");            //Layer
            fw.write("0\n");
            fw.write(" 10\n");            //X-coordinate
            fw.write((plItem.getX()+gap/2)+"\n");
            fw.write(" 20\n");            //Y-coordinate
            fw.write((plItem.getY()+plItem.getItemWidth()-gap/2)+"\n");
            fw.write("  0\n");            //'Termination attributes-follow flag 66
            fw.write("SEQEND\n");
            //
            fw.write("  0\n");             //        0
            fw.write("TEXT\n");            //        TEXT
            fw.write("  8\n");             //        8
            fw.write("Label\n");           //        label
            fw.write(" 39\n");             //        39
            fw.write("20\n");              //        20
            fw.write(" 10\n");             //X-coordinate
            fw.write((plItem.getX()+gap/2+5)+"\n");
            fw.write(" 20\n");             //Y-coordinate
            fw.write((plItem.getY()+gap/2+5)+"\n");
            fw.write(" 40\n");
            fw.write("5\n");
            fw.write(" 41\n");
            fw.write("1\n");
            fw.write("  1\n");
            fw.write(plItem.getItemName()+"+"+plItem.getItemQty()+"Pcs.+PL"+plItem.getItemThickness()+"+"+plItem.getItemGrade()+"\n");
            fw.write("  7\n");
            fw.write("ROMANS\n");
            //
            fw.write("  0\n");            //'Section ends
            fw.write("ENDSEC\n");
        }

     void drawText(FileWriter fw, String text, String textX, String textY) throws IOException {

        fw.write("  0\n");          //'Each section starts with group code 0
        fw.write("SECTION\n");
        fw.write("  2\n");          //'Then, there's group code 2 ,followed by the name of the section
        fw.write("ENTITIES\n");
        //
        fw.write("  0\n");             //        0
        fw.write("TEXT\n");            //        TEXT
        fw.write("  8\n");             //        8
        fw.write("Label\n");           //        label
        fw.write(" 39\n");             //        39
        fw.write("20\n");              //        20
        fw.write(" 10\n");             //X-coordinate
        fw.write(textX+"\n");
        fw.write(" 20\n");             //Y-coordinate
        fw.write(textY+"\n");
        fw.write(" 40\n");
        fw.write("5\n");
        fw.write(" 41\n");
        fw.write("1\n");
        fw.write("  1\n");
        fw.write(text+"\n");
        fw.write("  7\n");
        fw.write("ROMANS\n");
        //
        fw.write("  0\n");            //'Section ends
        fw.write("ENDSEC\n");
    }
}