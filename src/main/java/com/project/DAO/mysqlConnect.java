package com.project.DAO;

import java.sql.*;

import javax.swing.JOptionPane;

public class mysqlConnect {
    public static Connection getConnection() {
        Connection c = null;
        try {
            // Dang ky MySQL Driver voi DriverManager
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // Các thống số database

            String url = "jdbc:mysql://localhost:6303/java_coffee";
            String userName = "root";
            String password = "#14425nqtNQT14425#";
            // Tao ket noi
            c = DriverManager.getConnection(url, userName, password);

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void printInfo(Connection c) {
        try {
            if (c != null) {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
