package com.project.Common;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.image.*;

public class ImageTableCellRenderer extends DefaultTableCellRenderer {
    private int width; // Chiều rộng của hình ảnh scaled
    private int height; // Chiều cao của hình ảnh scaled

    public ImageTableCellRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        // Gọi phương thức của superclass để lấy JLabel mặc định
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        // Kiểm tra nếu giá trị của cell là ImageIcon
        if (value instanceof ImageIcon) {
            // Scale hình ảnh
            ImageIcon originalIcon = (ImageIcon) value;
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Thiết lập hình ảnh scaled cho JLabel
            label.setIcon(scaledIcon);
            label.setText(null); // Xóa văn bản

            // Căn giữa hình ảnh trong cell
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
        }

        return label;
    }
}
