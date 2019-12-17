package com.illya.service.replacment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReplaceLineNC1 {
    private File file;
    private String fileName, newMaterialText, newProjectText;
    private  ArrayList <String> linesOfFile = new ArrayList();

    public ReplaceLineNC1(File file, String newProjectText, String newMaterialText) {
//        System.out.println("--ReplaceLineNC1--");
        this.file=file;
        this.newProjectText=newProjectText;
        this.newMaterialText=newMaterialText;

        try {
            initReplaceLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void initReplaceLine() throws IOException {
//        System.out.println("--initReplaceLine--"+file.getAbsolutePath());
        fileName = file.getName().replace(".nc1","");
//        System.out.println(fileName);

        int i=0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if(i==2){
                    linesOfFile.add("  " + newProjectText);
                }else if(i==4 || i==5){
                    linesOfFile.add("  "+fileName+"x");
                }else if(i==6){
                    linesOfFile.add("  "+newMaterialText);
                }else{
                    linesOfFile.add(str);
                }
                i++;
//                System.out.println(str);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("linesOfFile: " + linesOfFile.get(1));
        FileWriter fw = new FileWriter(fileName+"x.nc1");
        for(String line:linesOfFile){
            fw.write(line+"\n");
        }
        fw.close();
    }
    public  ArrayList getLinesOfFile() {
        return linesOfFile;
    }
}