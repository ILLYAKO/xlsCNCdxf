package com.illya.view;

import javax.swing.*;

public class GuiMain {

    public GuiMain() {
        initGuiMain();
    }

    public void initGuiMain() {
        JFrame f = new JFrame("XLS Converter and LinearNest. ©ILLYA");
        GuiMenuBar mb = new GuiMenuBar(f);
        f.setSize(500, 150);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }
}