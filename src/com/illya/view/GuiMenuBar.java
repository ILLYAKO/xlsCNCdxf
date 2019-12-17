package com.illya.view;

import com.illya.service.FileChooser;
import com.illya.service.replacment.FolderChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GuiMenuBar {
    JMenu menuFile, menuConvert, menuNest, menuHelp, submenu;
    JMenuItem iFileNew, iFileOpen, iFileSave, iFilePrint, iFileExit, iHelpAbout;
    JMenuItem iConvertToCNC, iConvertToDXF, iLinearNest, iPlateNest;
    JMenuItem linesReplace;

    private String sourceUrl = null;
    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public GuiMenuBar(JFrame fr) {
        initGuiMenuBar(fr);
    }

    public void initGuiMenuBar(JFrame fr) {

        JMenuBar mb = new JMenuBar();
        menuFile = new JMenu("File");
        menuConvert =new JMenu("Convert");
        menuNest = new JMenu("Nesting");
        menuHelp = new JMenu("Help");
        submenu = new JMenu("Sub Menu");

        iFileNew = new JMenuItem("New");
        iFileOpen = new JMenuItem("Open");
        iFileSave = new JMenuItem("Save");
        iFilePrint = new JMenuItem("Print");
        iFileExit = new JMenuItem("Exit");
        iHelpAbout = new JMenuItem("About");
        iConvertToCNC = new JMenuItem("to CNC");
        iConvertToDXF= new JMenuItem("to DXF");
        iLinearNest= new JMenuItem("Linear Nest");
        iPlateNest= new JMenuItem("Plate Nest");
        linesReplace= new JMenuItem("Replace Lines");


        iFileExit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        iConvertToCNC.addActionListener((ActionEvent event) -> {
            new FileChooser("CNC");
        });
        iConvertToDXF.addActionListener((ActionEvent event) -> {
            new FileChooser("DXF");
        });
        linesReplace.addActionListener((ActionEvent event) -> {
            new FolderChooser("LINESREPLACE");
        });
        iLinearNest.addActionListener((ActionEvent event) -> {
            new FileChooser("LINEARNEST");
        });
        iPlateNest.addActionListener((ActionEvent event) -> {
            new FileChooser("PLATENEST");
        });

        menuFile.add(iFileNew);
        menuFile.add(iFileOpen);
        menuFile.add(iFileSave);
        menuFile.add(iFilePrint);
        menuFile.add(iFileExit);
        menuHelp.add(iHelpAbout);
        menuConvert.add(iConvertToCNC);
        menuConvert.add(iConvertToDXF);
        menuConvert.add(linesReplace);
        menuNest.add(iLinearNest);
        menuNest.add(iPlateNest);

        mb.add(menuFile);
        mb.add(menuConvert);
        mb.add(menuNest);
        mb.add(menuHelp);
        fr.setJMenuBar(mb);
    }
}