package com.project.DAO;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.JOptionPane;

import com.project.DTO.SupplierDTO;

public class SupplierDAO {

    public static Boolean createSupplier(SupplierDTO newSupplier) {
        boolean success = false;
        try (
                Connection conn = mysqlConnect.getConnection();

                PreparedStatement pst = conn.prepareStatement(
                        "INSERT INTO NhaCungCap (ten_ncc, dia_chi, dien_thoai, email) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, newSupplier.getName_supplier());
            pst.setString(2, newSupplier.getAddress());
            pst.setString(3, newSupplier.getPhone());
            pst.setString(4, newSupplier.getEmail());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            } else {
                success = false;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Mã lỗi 1062 là mã lỗi của MySQL cho lỗi duy nhất
                JOptionPane.showMessageDialog(null, "Tên nhà cung cấp đã tồn tại");
                return false;
            }
            e.printStackTrace();
        }

        return success;
    }

    public static ArrayList<SupplierDTO> get_all_supplier() {
        ArrayList<SupplierDTO> list_supplier = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "select * from NhaCungCap where is_active = ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, 1);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name_supplier = result_query.getString("ten_ncc");
                String address = result_query.getString("dia_chi");
                String phone = result_query.getString("dien_thoai");
                String email = result_query.getString("email");
                Boolean is_active = result_query.getBoolean("is_active");
                Timestamp createdAt = result_query.getTimestamp("createdAt");
                Timestamp updatedAt = result_query.getTimestamp("updatedAt");
                LocalDateTime createdAtFormated = createdAt.toLocalDateTime();
                LocalDateTime updatedAtFormated = updatedAt != null ? updatedAt.toLocalDateTime() : null;

                list_supplier.add(new SupplierDTO(id, name_supplier, address, phone, email, is_active,
                        createdAtFormated, updatedAtFormated));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_supplier;

    }

    public static Boolean updateSupplier(SupplierDTO updateSupplier) {
        Boolean success = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "UPDATE NhaCungCap SET ten_ncc = ?, dia_chi = ?, dien_thoai = ?, email = ? , updatedAt = NOW() WHERE id = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, updateSupplier.getName_supplier());
            pst.setString(2, updateSupplier.getAddress());
            pst.setString(3, updateSupplier.getPhone());
            pst.setString(4, updateSupplier.getEmail());
            pst.setInt(5, updateSupplier.getId());

            int rowAffected = pst.executeUpdate();

            if (rowAffected > 0) {
                success = true;
            } else {
                success = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return success;
    }

    public static Boolean ChangeIsActiveSupplier(int id, Boolean is_active) {
        Boolean success = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "UPDATE NhaCungCap SET is_active = ?, updatedAt = NOW() WHERE id = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, is_active ? 1 : 0);
            pst.setInt(2, id);

            int rowAffected = pst.executeUpdate();

            if (rowAffected > 0) {
                success = true;
            } else {
                success = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return success;
    }

    public static ArrayList<SupplierDTO> SearchSupplierByName(String search_supplier) {
        ArrayList<SupplierDTO> list_supplier = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "select * from NhaCungCap where ten_ncc  like ? and is_active = 1";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, search_supplier + "%");

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name_supplier = result_query.getString("ten_ncc");
                String address = result_query.getString("dia_chi");
                String phone = result_query.getString("dien_thoai");
                String email = result_query.getString("email");
                Boolean is_active = result_query.getBoolean("is_active");
                Timestamp createdAt = result_query.getTimestamp("createdAt");
                Timestamp updatedAt = result_query.getTimestamp("updatedAt");
                LocalDateTime createdAtFormated = createdAt.toLocalDateTime();
                LocalDateTime updatedAtFormated = updatedAt != null ? updatedAt.toLocalDateTime() : null;

                list_supplier.add(new SupplierDTO(id, name_supplier, address, phone, email, is_active,
                        createdAtFormated, updatedAtFormated));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_supplier;
    }

    public static ArrayList<SupplierDTO> SearchSupplierByEmail(String search_supplier) {
        ArrayList<SupplierDTO> list_supplier = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "select * from NhaCungCap where email  like ? and is_active = 1";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, search_supplier + "%");

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name_supplier = result_query.getString("ten_ncc");
                String address = result_query.getString("dia_chi");
                String phone = result_query.getString("dien_thoai");
                String email = result_query.getString("email");
                Boolean is_active = result_query.getBoolean("is_active");
                Timestamp createdAt = result_query.getTimestamp("createdAt");
                Timestamp updatedAt = result_query.getTimestamp("updatedAt");
                LocalDateTime createdAtFormated = createdAt.toLocalDateTime();
                LocalDateTime updatedAtFormated = updatedAt != null ? updatedAt.toLocalDateTime() : null;

                list_supplier.add(new SupplierDTO(id, name_supplier, address, phone, email, is_active,
                        createdAtFormated, updatedAtFormated));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_supplier;
    }

}
