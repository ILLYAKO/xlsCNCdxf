package com.illya.service;

import com.illya.model.ItemBar;
import com.illya.service.nesting.LinearNest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LinesToLinearNesting {
    private ArrayList items = new ArrayList();
    private ArrayList plateNestings;
    private ItemBar item;
    private String fileName = "LinearNest";

    public LinesToLinearNesting(ArrayList arrayLines, String textRawL, String textGap) {
        arrayLines.forEach(line->{
            String[] names = line.toString().split(",");
            item = new ItemBar(
                            names[0],
                            names[1],
                            Integer.parseInt(names[2]),
                            names[3],
                            Integer.parseInt(names[4]),
                            names[5]
            );
            items.add(item);
        });

        LinearNest nests = new LinearNest(items, textRawL); // <<<<<<<<<<<<<<<<<<<----------------------------

        try {
            body(nests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void body (LinearNest nests) throws IOException {
        ArrayList tempNests = nests.getNests();
        FileWriter fw = new FileWriter(fileName+".csv");
        fw.write("Linear Nest \n\n");

        for (int i = 0; i < tempNests.size(); i++) {
            fw.write("Nest No: " + (i+1) + "\n");
            fw.write("Profile,  Part,  Length(mm)\n");
            ArrayList <ItemBar> nest = ( ArrayList <ItemBar> ) tempNests.get(i);
            int totalLenght =0;
            for (ItemBar element : nest) {
                fw.write(element.getItemProfile()+", " +element.getItemName()+", "+element.getItemLength()+ "\n");
                totalLenght+=element.getItemLength();
            }
            fw.write(",Total:,"+totalLenght+"\n");
            fw.write("------------------\n\n");
        }
        fw.close();
    }
}