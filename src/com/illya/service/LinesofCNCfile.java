package com.illya.service;

import java.io.FileWriter;
import java.io.IOException;

public class LinesofCNCfile {

    private String project, name, qty;
    private float thickness, width, length;
    private float massUnit, paintArea;
    private String grade;

    public LinesofCNCfile(String components ) {
        String[] names =components.split(",");
        project   = names[0];System.out.println(project);
        name      = names[1];System.out.println(name);
        qty       = names[2];System.out.println(qty);
        thickness = Float.parseFloat(names[3]);System.out.println(thickness);
        width     = Float.parseFloat(names[4]);System.out.println(width);
        length    = Float.parseFloat(names[5]);System.out.println(length);
        grade     = names[6];System.out.println(grade);
        massUnit  = length*width*thickness*0.00000785f;
        paintArea = 0.000002f*(length*width + thickness*(length+width));
        try {
            body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void body () throws IOException {
        FileWriter fw = new FileWriter(name+".nc1");
        fw.write(String.format("%s%n", "ST"));
        fw.write(String.format("%-3s%s%s%n", "**", name, ".nc1"));
        fw.write(String.format("%-2s%s%n", "", project));
        fw.write(String.format("%-2s%d%n", "", 1));
        fw.write(String.format("%-2s%s%n", "", name));
        fw.write(String.format("%-2s%s%n", "", name));
        fw.write(String.format("%-2s%s%n", "", grade));
        fw.write(String.format("%-2s%s%n", "", qty));
        fw.write(String.format("%-2s%s%s%n", "","PL", thickness));
        fw.write(String.format("%-2s%s%n", "", "B"));
        fw.write(String.format("%11.2f%n", length));
        fw.write(String.format("%11.2f%n", width));
        fw.write(String.format("%11.2f%n", thickness));
        fw.write(String.format("%11.2f%n", thickness));
        fw.write(String.format("%11.2f%n", thickness));
        fw.write(String.format("%11.2f%n", Float.parseFloat("0")));
        fw.write(String.format("%11.3f%n", massUnit));
        fw.write(String.format("%11.3f%n", paintArea));
        fw.write(String.format("%11.3f%n", Float.parseFloat("0")));
        fw.write(String.format("%11.3f%n", Float.parseFloat("0")));
        fw.write(String.format("%11.3f%n", Float.parseFloat("0")));
        fw.write(String.format("%11.3f%n", Float.parseFloat("0")));
        fw.write("\n");
        fw.write("\n");
        fw.write("\n");
        fw.write("\n");
        fw.write(String.format("%s%n", "AK"));
        fw.write(String.format("%3s%11.2f%s%10.2f%11.2f%11.2f%11.2f%11.2f%11.2f%n", "v",0.00f,"u",0.00f,0.00f,0.00f,0.00f,0.00f,0.00f));
        fw.write(String.format("%14.2f%11.2f%11.2f%11.2f%11.2f%11.2f%11.2f%n", length,0.00f,0.00f,0.00f,0.00f,0.00f,0.00f));
        fw.write(String.format("%14.2f%11.2f%11.2f%11.2f%11.2f%11.2f%11.2f%n", length,width,0.00f,0.00f,0.00f,0.00f,0.00f));
        fw.write(String.format("%14.2f%11.2f%11.2f%11.2f%11.2f%11.2f%11.2f%n", 0.00f,width,0.00f,0.00f,0.00f,0.00f,0.00f));
        fw.write(String.format("%14.2f%11.2f%11.2f%11.2f%11.2f%11.2f%11.2f%n", 0.00f,0.00f,0.00f,0.00f,0.00f,0.00f,0.00f));
        fw.write(String.format("%s%n", "EN"));
        fw.close();
    }
}