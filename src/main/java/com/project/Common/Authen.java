package com.project.Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Authen {
    public static Boolean validateLogin(String email, String pass) {
        if (pass.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin !");
            return false;
        }
        Boolean check_email = Pattern.matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
        if (!check_email) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ");
            return false;
        }
        return true;
    }

    public static boolean validateSignup(String email, String pass, String name, String phone) {
        if (email.isEmpty() || pass.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
            return false;
        }
        Boolean check_email = Pattern.matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
        if (!check_email) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ");
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        Boolean check_email = Pattern.matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
        if (!check_email) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ");
            return false;
        }
        return true;
    }

    public static String formatDateTime(LocalDateTime inputDate) {
        // Define the input and output formats
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Format the input LocalDateTime to the desired format
        String formattedDate = inputDate.format(inputFormatter);

        // Parse the formatted date string back to LocalDateTime
        return LocalDateTime.parse(formattedDate, inputFormatter).format(outputFormatter);
    }

    public static void main(String[] args) {
        // System.out.println(Authen.formatDateTime(?"2024-03-27'T'23:50:16"));
    }
}
