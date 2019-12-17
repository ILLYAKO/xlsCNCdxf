package com.illya.service.replacment;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FolderChooser {

    private String sourceUrl = null;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public FolderChooser(String choice) {
        initUI(choice);
    }

    public void initUI(String choice) {
//        System.out.println("--Folder Chooser--");

        JFrame frame = new JFrame("Choose Folder");
        JPanel panelMain = new JPanel();
        JPanel panelUp = new JPanel();
        JPanel panelDown = new JPanel();
        JTextField textProjectField;
        JTextField textMaterialField;
        JLabel label1;

        label1 = new JLabel("Choose Material Grade & Folder",JLabel.CENTER);
        textMaterialField =new JTextField("New Material Grade",10);
        textMaterialField.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textMaterialField.setText("");
                }
            });

        textProjectField =new JTextField("New Project Name",10);
        textProjectField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textProjectField.setText("");
            }
        });


        JButton quitButton = new JButton("Exit");
        quitButton.setBounds(50, 100, 80, 30);

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        JButton browserButton = new JButton("Browser");
        browserButton.setBounds(100, 100, 80, 30);
        browserButton.addActionListener((ActionEvent event) -> {
//            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());

            JFileChooser jfc = new JFileChooser(new java.io.File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "NC1", "nc1", "cnc");
            jfc.setFileFilter(filter);
            jfc.setDialogTitle("Choose a directory:");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);


            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory: "+jfc.getCurrentDirectory());
                File folder = jfc.getCurrentDirectory();
                File[] listOfFiles = folder.listFiles();
                for (int i = 0; i < listOfFiles.length; i++)
                {
                    if(listOfFiles[i].getName().toLowerCase().endsWith("nc1")){
                        ReplaceLineNC1 replaceLine = new ReplaceLineNC1( listOfFiles[i],textProjectField.getText(), textMaterialField.getText() );//<<<<<<<<<---
                    }
                }
            }
        });

        panelUp.add(label1);
        panelDown.add(textProjectField);
        panelDown.add(textMaterialField);


        panelDown.add(browserButton);
        panelDown.add(quitButton);

        panelMain.add(panelUp);
        panelMain.add(panelDown);
        frame.add(panelMain);

        frame.setSize(450, 120);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}