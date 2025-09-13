package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.project.DTO.ProductDTO;
import com.project.DTO.StatisticalDTO;
import com.project.DTO.StatisticalProductDTO;

public class StatisticalDAO {

    public static ArrayList<StatisticalDTO> getProfitRevenueExpense(String startDate, String endDate) {
        ArrayList<StatisticalDTO> data = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT " +
                    "SUM(doanh_thu) AS tong_doanh_thu, " +
                    "SUM(chi_phi_nhap_hang) AS tong_chi_phi_nhap_hang, " +
                    "SUM(doanh_thu) - SUM(chi_phi_nhap_hang) AS loi_nhuan, " +
                    "ngay " +
                    "FROM ( " +
                    "SELECT " +
                    "SUM(SP.gia * CTHD.so_luong) AS doanh_thu, " +
                    "0 AS chi_phi_nhap_hang, " +
                    "DATE_FORMAT(HoaDon.createdAt, '%Y-%m-%d') AS ngay " +
                    "FROM " +
                    "HoaDon " +
                    "JOIN " +
                    "ChiTietHoaDon CTHD ON HoaDon.id = CTHD.HoaDon_id " +
                    "JOIN " +
                    "SanPham SP ON CTHD.SanPham_id = SP.id " +
                    "WHERE " +
                    "HoaDon.createdAt BETWEEN ? AND ? " + // Thêm điều kiện ngày tháng ở đây
                    "AND HoaDon.trang_thai = 'successful' " + // Thêm điều kiện ngày tháng ở đây
                    "GROUP BY " +
                    "ngay " +

                    "UNION ALL " +

                    "SELECT " +
                    "0 AS doanh_thu, " +
                    "SUM(CTPN.don_gia * CTPN.so_luong) AS chi_phi_nhap_hang, " +
                    "DATE_FORMAT(PhieuNhap.createdAt, '%Y-%m-%d') AS ngay " +
                    "FROM " +
                    "PhieuNhap " +
                    "JOIN " +
                    "ChiTietPhieuNhap CTPN ON PhieuNhap.id = CTPN.PhieuNhap_id " +
                    "WHERE " +
                    "PhieuNhap.createdAt BETWEEN ? AND ? " + // Thêm điều kiện ngày tháng ở đây
                    "AND PhieuNhap.is_active = 1 " +
                    "GROUP BY " +
                    "ngay " +
                    ") AS loi_nhuan_ngay " +
                    "GROUP BY " +
                    "ngay;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startDate);
            pst.setString(2, endDate);
            pst.setString(3, startDate);
            pst.setString(4, endDate);
            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                float revenue = result_query.getFloat("tong_doanh_thu");
                float expense = result_query.getFloat("tong_chi_phi_nhap_hang");
                float profit = result_query.getFloat("loi_nhuan");
                Date day = result_query.getDate("ngay");

                LocalDate dayAtFormated = day.toLocalDate();

                data.add(new StatisticalDTO(revenue, expense, profit, dayAtFormated));
            }
            return data;
        } catch (Exception e) {

            return null;
            // TODO: handle exception
        }

    }

    public static ArrayList<StatisticalDTO> getProfitRevenueExpenseFor30Day() {
        ArrayList<StatisticalDTO> data = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT " +
                    "SUM(doanh_thu) AS tong_doanh_thu, " +
                    "SUM(chi_phi_nhap_hang) AS tong_chi_phi_nhap_hang, " +
                    "SUM(doanh_thu) - SUM(chi_phi_nhap_hang) AS loi_nhuan, " +
                    "ngay " +
                    "FROM ( " +
                    "SELECT " +
                    "SUM(SP.gia * CTHD.so_luong) AS doanh_thu, " +
                    "0 AS chi_phi_nhap_hang, " +
                    "DATE(HoaDon.createdAt) AS ngay " +
                    "FROM " +
                    "HoaDon " +
                    "JOIN " +
                    "ChiTietHoaDon CTHD ON HoaDon.id = CTHD.HoaDon_id " +
                    "JOIN " +
                    "SanPham SP ON CTHD.SanPham_id = SP.id " +
                    "WHERE " +
                    "HoaDon.createdAt >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
                    "AND HoaDon.trang_thai = 'successful' " +
                    "GROUP BY " +
                    "DATE(HoaDon.createdAt) " +

                    "UNION ALL " +

                    "SELECT " +
                    "0 AS doanh_thu, " +
                    "SUM(CTPN.don_gia * CTPN.so_luong) AS chi_phi_nhap_hang, " +
                    "DATE(PhieuNhap.createdAt) AS ngay " +
                    "FROM " +
                    "PhieuNhap " +
                    "JOIN " +
                    "ChiTietPhieuNhap CTPN ON PhieuNhap.id = CTPN.PhieuNhap_id " +
                    "WHERE " +
                    "PhieuNhap.createdAt >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
                    "AND PhieuNhap.is_active = 1 " +
                    "GROUP BY " +
                    "DATE(PhieuNhap.createdAt) " +
                    ") AS loi_nhuan_ngay " +
                    "GROUP BY " +
                    "ngay";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                float revenue = result_query.getFloat("tong_doanh_thu");
                float expense = result_query.getFloat("tong_chi_phi_nhap_hang");
                float profit = result_query.getFloat("loi_nhuan");
                Date day = result_query.getDate("ngay");

                LocalDate dayAtFormated = day.toLocalDate();

                data.add(new StatisticalDTO(revenue, expense, profit, dayAtFormated));
            }
            return data;
        } catch (Exception e) {

            return null;
            // TODO: handle exception
        }

    }

    public static ArrayList<StatisticalDTO> getProfitRevenueExpenseByMonth() {
        ArrayList<StatisticalDTO> data = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT " +
                    "SUM(doanh_thu) AS tong_doanh_thu, " +
                    "SUM(chi_phi_nhap_hang) AS tong_chi_phi_nhap_hang, " +
                    "SUM(doanh_thu) - SUM(chi_phi_nhap_hang) AS loi_nhuan, " +
                    "thang " +
                    "FROM ( " +

                    "SELECT " +
                    "SUM(SP.gia * CTHD.so_luong) AS doanh_thu, " +
                    "0 AS chi_phi_nhap_hang, " +
                    "DATE_FORMAT(HoaDon.createdAt, '%Y-%m') AS thang " +
                    "FROM " +
                    "HoaDon " +
                    "JOIN " +
                    "ChiTietHoaDon CTHD ON HoaDon.id = CTHD.HoaDon_id " +
                    "JOIN " +
                    "SanPham SP ON CTHD.SanPham_id = SP.id " +
                    "WHERE " +
                    "HoaDon.trang_thai = 'successful' " +
                    "GROUP BY " +
                    "DATE_FORMAT(HoaDon.createdAt, '%Y-%m') " +

                    "UNION ALL " +

                    "SELECT " +
                    "0 AS doanh_thu, " +
                    "SUM(CTPN.don_gia * CTPN.so_luong) AS chi_phi_nhap_hang, " +
                    "DATE_FORMAT(PhieuNhap.createdAt, '%Y-%m') AS thang " +
                    "FROM " +
                    "PhieuNhap " +
                    "JOIN " +
                    "ChiTietPhieuNhap CTPN ON PhieuNhap.id = CTPN.PhieuNhap_id " +
                    "GROUP BY " +
                    "DATE_FORMAT(PhieuNhap.createdAt, '%Y-%m') " +
                    ") AS loi_nhuan_ngay " +
                    "GROUP BY " +
                    "thang;";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                float revenue = result_query.getFloat("tong_doanh_thu");
                float expense = result_query.getFloat("tong_chi_phi_nhap_hang");
                float profit = result_query.getFloat("loi_nhuan");
                String day = result_query.getString("thang");

                data.add(new StatisticalDTO(revenue, expense, profit, day));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            // TODO: handle exception
        }

    }

    public static float getTotalProfit() {
        float total_profit = 0;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT\n" +
                    "    SUM(doanh_thu) AS tong_doanh_thu,\n" +
                    "    SUM(chi_phi_nhap_hang) AS tong_chi_phi_nhap_hang,\n" +
                    "    SUM(doanh_thu) - SUM(chi_phi_nhap_hang) AS loi_nhuan\n" +
                    "FROM\n" +
                    "    (\n" +
                    "        SELECT\n" +
                    "            SUM(SP.gia * CTHD.so_luong) AS doanh_thu,\n" +
                    "            0 AS chi_phi_nhap_hang,\n" +
                    "            DATE(HoaDon.createdAt) AS ngay\n" +
                    "        FROM\n" +
                    "            HoaDon\n" +
                    "        JOIN\n" +
                    "            ChiTietHoaDon CTHD ON HoaDon.id = CTHD.HoaDon_id\n" +
                    "        JOIN\n" +
                    "            SanPham SP ON CTHD.SanPham_id = SP.id\n" +
                    "        WHERE\n" +
                    "            HoaDon.trang_thai = 'successful'\n" +
                    "        GROUP BY\n" +
                    "            DATE(HoaDon.createdAt)\n" +
                    "\n" +
                    "        UNION ALL\n" +
                    "\n" +
                    "        SELECT\n" +
                    "            0 AS doanh_thu,\n" +
                    "            SUM(CTPN.don_gia * CTPN.so_luong) AS chi_phi_nhap_hang,\n" +
                    "            DATE(PhieuNhap.createdAt) AS ngay\n" +
                    "        FROM\n" +
                    "            PhieuNhap\n" +
                    "        JOIN\n" +
                    "            ChiTietPhieuNhap CTPN ON PhieuNhap.id = CTPN.PhieuNhap_id\n" +
                    "        WHERE\n" +
                    "            PhieuNhap.is_active = 1\n" +
                    "        GROUP BY\n" +
                    "            DATE(PhieuNhap.createdAt)\n" +
                    "    ) AS loi_nhuan_ngay;";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                // float revenue = result_query.getFloat("tong_doanh_thu");
                // float expense = result_query.getFloat("tong_chi_phi_nhap_hang");
                float profit = result_query.getFloat("loi_nhuan");

                total_profit = profit;
            }
            return total_profit;
        } catch (Exception e) {

            return 0;
            // TODO: handle exception
        }

    }

    public static int getTotalProduct() {
        int total_product = 0;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT \r\n" + //
                    "COUNT(SanPham.id) AS total_product\r\n" + //
                    "FROM SanPham\r\n" + //
                    " where is_active = 1 ";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                total_product = result_query.getInt("total_product");

            }
            return total_product;
        } catch (Exception e) {
            return 0;
            // TODO: handle exception
        }

    }

    public static int getTotalOrder() {
        int total_order = 0;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT \r\n" + //
                    "COUNT(HoaDon.id) AS total_order\r\n" + //
                    "FROM HoaDon\r\n" + //
                    "";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                total_order = result_query.getInt("total_order");

            }
            return total_order;
        } catch (Exception e) {
            return 0;
            // TODO: handle exception
        }

    }

    public static int getTotalSupplier() {
        int total_supplier = 0;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT \r\n" + //
                    "COUNT(NhaCungCap.id) AS total_supplier\r\n" + //
                    "FROM NhaCungCap\r\n" + //
                    " where is_active = 1 ";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                total_supplier = result_query.getInt("total_supplier");

            }
            return total_supplier;
        } catch (Exception e) {
            return 0;
            // TODO: handle exception
        }

    }

    public static int getTotalEmployee() {
        int total_employee = 0;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT \r\n" + //
                    "COUNT(NhanVien.id) AS total_employee\r\n" + //
                    "FROM NhanVien\r\n" + //
                    " ";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                total_employee = result_query.getInt("total_employee");

            }
            return total_employee;
        } catch (Exception e) {
            return 0;
            // TODO: handle exception
        }

    }

    public static int getTotalIngredient() {
        int total_ingredient = 0;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT \r\n" + //
                    "COUNT(NguyenLieu.id) AS total_ingredient\r\n" + //
                    "FROM NguyenLieu\r\n" + //
                    " where is_active = 1 ";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                total_ingredient = result_query.getInt("total_ingredient");

            }
            return total_ingredient;
        } catch (Exception e) {
            return 0;
            // TODO: handle exception
        }

    }

    // statistical product

    public static StatisticalProductDTO getBestSellerProduct() {
        StatisticalProductDTO product = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "    SanPham.ten_SP, " +
                    "    SanPham.url_anh, " +
                    "    SanPham.gia, " +
                    "    SUM(cthd.so_luong) AS total_sold " +
                    "FROM HoaDon " +
                    "JOIN ChiTietHoaDon cthd ON HoaDon.id = cthd.HoaDon_id " +
                    "JOIN SanPham ON cthd.SanPham_id = SanPham.id " +
                    "GROUP BY SanPham.ten_SP, SanPham.url_anh, SanPham.gia " +
                    "ORDER BY total_sold DESC " +
                    "LIMIT 1;";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_product = result_query.getString("ten_SP");
                String url_img = result_query.getString("url_anh");
                float price = result_query.getFloat("gia");
                int total_sold = result_query.getInt("total_sold");
                product = new StatisticalProductDTO(name_product, url_img, price, total_sold);
            }

        } catch (Exception e) {
            return null;
        }
        return product;
    }

    public static StatisticalProductDTO getBestRevenueProduct() {
        StatisticalProductDTO product = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "SanPham.ten_SP, " +
                    "SanPham.url_anh, " +
                    "SanPham.gia, " +
                    "SUM(cthd.so_luong * SanPham.gia) AS doanh_thu " +
                    "FROM " +
                    "HoaDon " +
                    "JOIN ChiTietHoaDon cthd ON HoaDon.id = cthd.HoaDon_id " +
                    "JOIN SanPham ON cthd.SanPham_id = SanPham.id " +
                    "GROUP BY " +
                    "SanPham.ten_SP, " +
                    "SanPham.url_anh, " +
                    "SanPham.gia " +
                    "ORDER BY " +
                    "doanh_thu DESC " +
                    "LIMIT 1;";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_product = result_query.getString("ten_SP");
                String url_img = result_query.getString("url_anh");
                float price = result_query.getFloat("gia");
                int total_sold = result_query.getInt("doanh_thu");
                product = new StatisticalProductDTO(name_product, url_img, price, total_sold);
            }

        } catch (Exception e) {
            return null;
        }
        return product;
    }

    public static ArrayList<StatisticalProductDTO> getTotalSoldByProduct() {
        ArrayList<StatisticalProductDTO> list_product = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "SanPham.id, " +
                    "SanPham.ten_SP, " +
                    "SanPham.url_anh, " +
                    "SanPham.gia, " +
                    "SUM(cthd.so_luong) AS total_sold " +
                    "FROM " +
                    "HoaDon " +
                    "JOIN ChiTietHoaDon cthd ON HoaDon.id = cthd.HoaDon_id " +
                    "JOIN SanPham ON cthd.SanPham_id = SanPham.id " +
                    "GROUP BY " +
                    "SanPham.id";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_product = result_query.getString("ten_SP");
                String url_img = result_query.getString("url_anh");
                float price = result_query.getFloat("gia");
                int total_sold = result_query.getInt("total_sold");
                list_product.add(new StatisticalProductDTO(name_product, url_img, price, total_sold));
            }

        } catch (Exception e) {
            return null;
        }
        return list_product;
    }

    public static ArrayList<StatisticalProductDTO> getTotalSoldByProductChooseDate(String startDate, String endDate) {
        ArrayList<StatisticalProductDTO> list_product = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "SanPham.id, " +
                    "SanPham.ten_SP, " +
                    "SanPham.url_anh, " +
                    "SanPham.gia, " +
                    "SUM(cthd.so_luong) AS total_sold, " +
                    "HoaDon.createdAt " +
                    "FROM " +
                    "HoaDon " +
                    "JOIN ChiTietHoaDon cthd ON HoaDon.id = cthd.HoaDon_id " +
                    "JOIN SanPham ON cthd.SanPham_id = SanPham.id " +
                    "WHERE " +
                    "HoaDon.createdAt BETWEEN ? AND ? " +
                    "GROUP BY " +
                    "SanPham.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startDate);
            pst.setString(2, endDate);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_product = result_query.getString("ten_SP");
                String url_img = result_query.getString("url_anh");
                float price = result_query.getFloat("gia");
                int total_sold = result_query.getInt("total_sold");
                list_product.add(new StatisticalProductDTO(name_product, url_img, price, total_sold));
            }

        } catch (Exception e) {
            return null;
        }
        return list_product;
    }

    public static ArrayList<StatisticalProductDTO> getQuantityByProduct() {
        ArrayList<StatisticalProductDTO> list_product = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT id,ten_SP,so_luong FROM SanPham WHERE is_active = 1";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_product = result_query.getString("ten_SP");
                int quantity = result_query.getInt("so_luong");
                list_product.add(new StatisticalProductDTO(name_product, quantity));
            }

        } catch (Exception e) {
            return null;
        }
        return list_product;
    }

    // warehouses
    public static ArrayList<StatisticalDTO> expenseImportWarehousesBy30day() {
        ArrayList<StatisticalDTO> list_data = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "SUM(ctph.so_luong * ctph.don_gia) AS tong_nhap, " +
                    "DATE_FORMAT(PhieuNhap.createdAt, '%Y-%m-%d') AS Ngay " +
                    "FROM " +
                    "PhieuNhap " +
                    "JOIN " +
                    "ChiTietPhieuNhap ctph ON PhieuNhap.id = ctph.PhieuNhap_id " +
                    "WHERE " +
                    "PhieuNhap.createdAt >= DATE_SUB(CURDATE(), INTERVAL 15 DAY) " +
                    "GROUP BY " +
                    "Ngay";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                float values = result_query.getFloat("tong_nhap");
                Date date = result_query.getDate("Ngay");
                LocalDate formatedDate = date.toLocalDate();
                list_data.add(new StatisticalDTO(values, formatedDate));
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return list_data;
    }

    public static ArrayList<StatisticalDTO> expenseImportWarehousesChooseDay(String startTime, String endTime) {
        ArrayList<StatisticalDTO> list_data = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "SUM(ctph.so_luong * ctph.don_gia) AS tong_nhap, " +
                    "DATE(PhieuNhap.createdAt) AS Ngay " +
                    "FROM " +
                    "PhieuNhap " +
                    "JOIN " +
                    "ChiTietPhieuNhap ctph ON PhieuNhap.id = ctph.PhieuNhap_id " +
                    "WHERE " +
                    "PhieuNhap.createdAt BETWEEN ? AND ? " +
                    "GROUP BY " +
                    "Ngay";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startTime);
            pst.setString(2, endTime);
            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                float values = result_query.getFloat("tong_nhap");
                Date date = result_query.getDate("Ngay");
                LocalDate formatedDate = date.toLocalDate();
                list_data.add(new StatisticalDTO(values, formatedDate));
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return list_data;
    }

    public static ArrayList<StatisticalDTO> StatisticalIngredient() {
        ArrayList<StatisticalDTO> list_data = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT ten_NL, so_luong FROM NguyenLieu WHERE is_active = 1 ";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                String values = result_query.getString("ten_NL");
                float quantity = result_query.getFloat("so_luong");

                list_data.add(new StatisticalDTO(values, quantity));
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return list_data;
    }

    public static void main(String[] args) {
        ArrayList<StatisticalDTO> data = expenseImportWarehousesBy30day();

        for (StatisticalDTO statisticalDTO : data) {
            System.out.println(statisticalDTO.getValues());
            System.out.println(statisticalDTO.getDate());
        }

    }
}
