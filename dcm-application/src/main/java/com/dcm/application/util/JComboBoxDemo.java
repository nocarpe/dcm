package com.dcm.application.util;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JComboBoxDemo extends JFrame {

    private String names[] = {"34E 165 80", "36F 166 90", "32B 170 78"};

    public JComboBoxDemo() {
        this.setTitle("登录界面");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 150);
        //this.setBounds(100, 100, 250, 100);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        //contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.setLayout(new GridLayout(6, 1));
        JLabel label = new JLabel("请选择:");
        contentPane.add(label);
        JComboBox comboBox = new JComboBox<String>(names);
        contentPane.add(comboBox);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == e.SELECTED) {
                    label.setText("您选择了:" + names[comboBox.getSelectedIndex()]);
                }
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JComboBoxDemo example = new JComboBoxDemo();
    }
}