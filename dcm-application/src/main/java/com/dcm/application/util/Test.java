package com.dcm.application.util;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author : yaoximing
 * @date : 2020-12-16
 * @description :
 **/
public class Test extends JFrame {

    private JLabel label1, label2;
    private String names[] = {};
    JComboBox<String> lbk;

    public Test() {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jFrame.setTitle("登录页面");
        jFrame.setSize(400, 150);
        jFrame.setVisible(true);
    }


}
