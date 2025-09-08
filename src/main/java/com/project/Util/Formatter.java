package com.project.Util;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.FlatClientProperties;

public class Formatter {

    public static String getFormatedPrice(Float price) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
        return nf.format(price);
    }

    public static void centerAlignTableCells(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public static void setBoldHeaderTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(244, 244, 244));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setReorderingAllowed(false);
    }

    public static void setPlaceHolder(JTextField txt, String placeHolderText) {
        txt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeHolderText);
        txt.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
    }

    public static void setStyleButtonLoginForm(JButton btn){
        btn.setBackground(new Color(115, 77, 153));
        btn.setForeground(Color.WHITE);
        // btn.setFocusPainted(false);
        // btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
    }
}
