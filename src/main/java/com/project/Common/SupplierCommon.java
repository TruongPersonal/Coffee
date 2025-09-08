package com.project.Common;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class SupplierCommon {
    public static Boolean validateCreateSupplier(String name_supplier, String address, String phone, String email) {
        if (name_supplier.isEmpty() || address.isEmpty() || phone.isEmpty()
                || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin !");
            return false;
        }
        if (phone.length() != 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
            return false;
        }
        Boolean check_email = Pattern
                .matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
        if (!check_email) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ");
            return false;
        }
        return true;
    }

   
}
