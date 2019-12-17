package com.illya.service;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FileChooser {

    private String sourceUrl = null;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public FileChooser(String choice) {
        initUI(choice);
    }

    public void initUI(String choice) {
        JFrame frame = new JFrame("Chose file1");
        JPanel panelMain = new JPanel();
        JPanel panelUp = new JPanel();
        JPanel panelDown = new JPanel();
        final JTextField textRawL;
        final JTextField textGap;
        JLabel label1;

        if(choice.equals("LINEARNEST") ) {
            label1 = new JLabel("csv: Project, Name, Qty, Profile, Length(mm), Grade",JLabel.CENTER);
            textRawL =new JTextField("Stock length",7);
            textRawL.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textRawL.setText("");
                }
            });
            textGap=new JTextField("gap",5);
            textGap.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textRawL.setText("");
                }
            });
        }
        else if(choice.equals("PLATENEST")) {
            label1 = new JLabel("csv: Project, Name, Qty, Thickness(mm), Width(mm), Length(mm), Grade",JLabel.CENTER);
            textRawL =new JTextField("lengthXwidth",10);
            textRawL.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textRawL.setText("");
                }
            });
            textGap=new JTextField("gap",5);
            textGap.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textRawL.setText("");
                }
            });

        }
        else if(choice.equals("MATERIALREPLACE")){
            label1 = new JLabel("Choose Material Grade & Folder",JLabel.CENTER);
            textRawL =new JTextField("New Material Grade",10);
            textRawL.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textRawL.setText("");
                }
            });
            textGap=new JTextField("gap",5);
            textGap.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textRawL.setText("");
                }
            });
        }
        else {
            label1 = new JLabel("csv: Project, Name, Qty, Thickness(mm), Width(mm), Length(mm), Grade", JLabel.CENTER);
            textRawL =new JTextField("0",7);
            textGap=new JTextField("gap",5);
        }

        JButton quitButton = new JButton("Exit");
        quitButton.setBounds(50, 100, 80, 30);
        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        JButton browserButton = new JButton("Browser");
        browserButton.setBounds(100, 100, 80, 30);
        browserButton.addActionListener((ActionEvent event) -> {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();

                setSourceUrl(selectedFile.getAbsolutePath());

                FileItemReader fileReader = new FileItemReader(selectedFile.getAbsolutePath());

                try {
                        FileItemWriter fileWriter = new FileItemWriter(
                                textRawL.getText(),
                                textGap.getText(),
                                choice,
                                fileReader.getLinesOfFile()
                        );
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        panelUp.add(label1);
        if (choice.equals("LINEARNEST")||choice.equals("PLATENEST")||choice.equals("MATERIALREPLACE")){
            panelDown.add(textRawL);
        }
        if (choice.equals("PLATENEST")){
            panelDown.add(textGap);
        }

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