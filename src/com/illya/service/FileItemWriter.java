package com.illya.service;

import java.io.IOException;
import java.util.ArrayList;

public class FileItemWriter {
    public FileItemWriter(String textRawL, String textGap, String choice, ArrayList lines) throws IOException {
        if(choice.equalsIgnoreCase("CNC")){
            lines.forEach(line -> {new LinesofCNCfile(line.toString());});
        }
        else if(choice.equalsIgnoreCase("DXF")) {
            lines.forEach(line -> {new LinesofDXFfile(line.toString());});
        }
        else if(choice.equalsIgnoreCase("LINEARNEST")) {
            new LinesToLinearNesting(lines, textRawL, textGap);
        }
        else if(choice.equalsIgnoreCase("PLATENEST")) {
            new LinesToPlateNesting(lines, textRawL, textGap);
        }
        else {System.out.println("The END.");}
    }
}