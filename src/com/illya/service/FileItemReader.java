package com.illya.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileItemReader {
    private static   String fileUrl;
    private static ArrayList linesOfFile = new ArrayList();

    public FileItemReader(String link) {
//        System.out.println("--FileItemReader--");
        this.fileUrl=link;
        initFileReader();
    }

    public static void initFileReader() {

        File file = new File(fileUrl);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                linesOfFile.add(str);
//                System.out.println(str);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("linesOfFile: " + linesOfFile.get(1));
    }
    public  ArrayList getLinesOfFile() {
        return linesOfFile;
    }
}