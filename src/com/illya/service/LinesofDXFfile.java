package com.illya.service;

import java.io.FileWriter;
import java.io.IOException;

public class LinesofDXFfile {

    private String project;
    private String name;
    private String qty;
    private String thickness;
    private String width;
    private String length;
    private String grade;

    public LinesofDXFfile(String components ) {
        String[] names =components.split(",");
        project   = names[0];//System.out.println(project);
        name      = names[1];//System.out.println(name);
        qty       = names[2];//System.out.println(qty);
        thickness = names[3];//System.out.println(thickness);
        width     = names[4];//System.out.println(width);
        length    = names[5];//System.out.println(length);
        grade     = names[6];//System.out.println(grade);
        try {
            body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void body () throws IOException {
        FileWriter fw = new FileWriter(name+".dxf");
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
        fw.write("0.0\n");
        fw.write(" 20\n");            //Y-coordinate
        fw.write("0.0\n");
        //-2
        fw.write("  0\n");            //'vertex entities - pick of object
        fw.write("VERTEX\n");
        fw.write("  8\n");            //Layer
        fw.write("0\n");
        fw.write(" 10\n");            //X-coordinate
        fw.write(length+"\n");
        fw.write(" 20\n");            //Y-coordinate
        fw.write("0.0\n");
        //-3
        fw.write("  0\n");            //'vertex entities - pick of object
        fw.write("VERTEX\n");
        fw.write("  8\n");            //Layer
        fw.write("0\n");
        fw.write(" 10\n");            //X-coordinate
        fw.write(length+"\n");
        fw.write(" 20\n");            //Y-coordinate
        fw.write(width+"\n");
        //-4
        fw.write("  0\n");            //'vertex entities - pick of object
        fw.write("VERTEX\n");
        fw.write("  8\n");            //Layer
        fw.write("0\n");
        fw.write(" 10\n");            //X-coordinate
        fw.write("0.0\n");
        fw.write(" 20\n");            //Y-coordinate
        fw.write(width+"\n");
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
        fw.write("3.0\n");
        fw.write(" 20\n");             //Y-coordinate
        fw.write("3.0\n");
        fw.write(" 40\n");
        fw.write("5\n");
        fw.write(" 41\n");
        fw.write("1\n");
        fw.write("  1\n");
        fw.write(name+"+"+qty+"Pcs.+PL"+thickness+"+"+grade+"\n");
        fw.write("  7\n");
        fw.write("ROMANS\n");
        //
        fw.write("  0\n");            //'Section ends
        fw.write("ENDSEC\n");
        fw.write("  0\n");            //'EOF marking the end of the file
        fw.write("EOF\n");
        fw.close();
    }
}