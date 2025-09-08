package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// import org.apache.poi.hpsf.Array;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.project.DTO.DeliveryBillDTO;
import com.project.DTO.DetailDeliveryBillDTO;
import com.project.DTO.DetailEnterCouponDTO;

public class DeliveryBillDAO {

    public static boolean createDeliveryBill(DeliveryBillDTO deliveryBill,
            ArrayList<DetailDeliveryBillDTO> list_detail) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet generatedKeys = null;
        try {
            conn = mysqlConnect.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO PhieuXuat(ten_phieu_xuat, TaiKhoan_id) VALUES(?,?)";
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, deliveryBill.getNameDeliveryBill());
            pst.setInt(2, deliveryBill.getAccountId());

            int affectedRow = pst.executeUpdate();
            int deliveryBillId = -1;
            if (affectedRow > 0) {
                generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    deliveryBillId = generatedKeys.getInt(1);
                    for (DetailDeliveryBillDTO detailDTO : list_detail) {
                        String sql2 = "INSERT INTO ChiTietPhieuXuat (PhieuXuat_id, NguyenLieu_id, so_luong) VALUES (?, ?, ?)";
                        pst2 = conn.prepareStatement(sql2);
                        pst2.setInt(1, deliveryBillId);
                        pst2.setInt(2, detailDTO.getIngradientId());
                        pst2.setInt(3, detailDTO.getQuantity());
                        pst2.executeUpdate();

                        boolean check = updateQuantityIngredient(conn, detailDTO.getIngradientId(),
                                detailDTO.getQuantity());
                        if (!check) {
                            conn.rollback();
                            return false;
                        }
                    }
                }
            }
            conn.commit();
            return true;

        } catch (SQLException e) {
            // Rollback transaction in case of exception
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            // Close resources in finally block to ensure they are always closed
            try {
                if (pst2 != null) {
                    pst2.close();
                }
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean updateQuantityIngredient(Connection conn, int id, int quantity) throws SQLException {
        String selectSql = "SELECT so_luong FROM NguyenLieu WHERE id = ?";
        String updateSql = "UPDATE NguyenLieu SET so_luong = ? WHERE id = ?";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    int oldQuantity = rs.getInt("so_luong");
                    if (oldQuantity < quantity) {
                        JOptionPane.showMessageDialog(null, "Không đủ số lượng nguyên liệu để xuất");
                        return false;
                    }

                    int newQuantity = oldQuantity - quantity;
                    updateStmt.setInt(1, newQuantity);
                    updateStmt.setInt(2, id);
                    updateStmt.executeUpdate();

                    return true;
                } else {
                    // Không tìm thấy nguyên liệu với id tương ứng
                    return false;
                }
            }
        }
    }

    public static ArrayList<DeliveryBillDTO> getDeliveryBill() {
        ArrayList<DeliveryBillDTO> list_deliveryBills = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "PhieuXuat.id, " +
                    "ten_phieu_xuat, " +
                    "PhieuXuat.createdAt, " +
                    "NhanVien.ho_va_ten, " +
                    "SUM(ChiTietPhieuXuat.so_luong) AS tong_kg " +
                    "FROM PhieuXuat " +
                    "left JOIN ChiTietPhieuXuat ON PhieuXuat.id = ChiTietPhieuXuat.PhieuXuat_id " +
                    "left JOIN TaiKhoan ON  TaiKhoan.id = PhieuXuat.TaiKhoan_id " +
                    "left JOIN NhanVien ON TaiKhoan.id = NhanVien.TaiKhoan_id " +
                    "WHERE PhieuXuat.is_active = 1 " +
                    "GROUP BY PhieuXuat.id";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet result = pst.executeQuery();
            list_deliveryBills = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("ten_phieu_xuat");
                Timestamp createdAt = result.getTimestamp("createdAt");
                String nameEmployee = result.getString("ho_va_ten");
                float totalKg = result.getFloat("tong_kg");
                LocalDateTime createdAtFormat = createdAt.toLocalDateTime();
                DeliveryBillDTO deliveryBill = new DeliveryBillDTO(id, name, nameEmployee, totalKg, createdAtFormat);
                list_deliveryBills.add(deliveryBill);
            }

        } catch (Exception e) {
            return null;
        }
        return list_deliveryBills;
    }

    // getDetailDeliveryBill
    public static ArrayList<DetailDeliveryBillDTO> getDetailDeliveryBill(int deliveryBillId) {
        ArrayList<DetailDeliveryBillDTO> list_details = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "ChiTietPhieuXuat.id, " +
                    "NguyenLieu.ten_NL, " +
                    "NguyenLieu.id as nl_id, " +
                    "ChiTietPhieuXuat.so_luong " +
                    "FROM ChiTietPhieuXuat " +
                    "JOIN NguyenLieu ON ChiTietPhieuXuat.NguyenLieu_id = NguyenLieu.id " +
                    "WHERE ChiTietPhieuXuat.PhieuXuat_id = ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, deliveryBillId);
            ResultSet result = pst.executeQuery();
            list_details = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("ten_NL");
                int ingredientId = result.getInt("nl_id");
                int quantity = result.getInt("so_luong");
                DetailDeliveryBillDTO detail = new DetailDeliveryBillDTO(id, name, ingredientId, quantity);
                list_details.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return list_details;

    }

    // SearchDeliveryBillByName
    public static ArrayList<DeliveryBillDTO> SearchDeliveryBillByName(String keyword) {
        ArrayList<DeliveryBillDTO> list_deliveryBills = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "PhieuXuat.id, " +
                    "ten_phieu_xuat, " +
                    "PhieuXuat.createdAt, " +
                    "NhanVien.ho_va_ten, " +
                    "SUM(ChiTietPhieuXuat.so_luong) AS tong_kg " +
                    "FROM PhieuXuat " +
                    "JOIN ChiTietPhieuXuat ON PhieuXuat.id = ChiTietPhieuXuat.PhieuXuat_id " +
                    "JOIN TaiKhoan ON  TaiKhoan.id = PhieuXuat.TaiKhoan_id " +
                    "JOIN NhanVien ON TaiKhoan.id = NhanVien.TaiKhoan_id " +
                    "WHERE PhieuXuat.is_active = 1 and ten_phieu_xuat like ?  " +
                    "GROUP BY PhieuXuat.id";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, keyword + "%");

            ResultSet result = pst.executeQuery();
            list_deliveryBills = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("ten_phieu_xuat");
                Timestamp createdAt = result.getTimestamp("createdAt");
                String nameEmployee = result.getString("ho_va_ten");
                float totalKg = result.getFloat("tong_kg");
                LocalDateTime createdAtFormat = createdAt.toLocalDateTime();
                DeliveryBillDTO deliveryBill = new DeliveryBillDTO(id, name, nameEmployee, totalKg, createdAtFormat);
                list_deliveryBills.add(deliveryBill);
            }

        } catch (Exception e) {
            return null;
        }
        return list_deliveryBills;
    }

    // searchDeliveryBillByDay
    public static ArrayList<DeliveryBillDTO> searchDeliveryBillByDay(String startTime, String endTime) {
        ArrayList<DeliveryBillDTO> list_deliveryBills = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "PhieuXuat.id, " +
                    "ten_phieu_xuat, " +
                    "PhieuXuat.createdAt, " +
                    "NhanVien.ho_va_ten, " +
                    "SUM(ChiTietPhieuXuat.so_luong) AS tong_kg " +
                    "FROM PhieuXuat " +
                    "JOIN ChiTietPhieuXuat ON PhieuXuat.id = ChiTietPhieuXuat.PhieuXuat_id " +
                    "JOIN TaiKhoan ON  TaiKhoan.id = PhieuXuat.TaiKhoan_id " +
                    "JOIN NhanVien ON TaiKhoan.id = NhanVien.TaiKhoan_id " +
                    "WHERE PhieuXuat.is_active = 1 and PhieuXuat.createdAt between ? and ? " +
                    "GROUP BY PhieuXuat.id";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startTime);
            pst.setString(2, endTime);
            ResultSet result = pst.executeQuery();
            list_deliveryBills = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("ten_phieu_xuat");
                Timestamp createdAt = result.getTimestamp("createdAt");
                String nameEmployee = result.getString("ho_va_ten");
                float totalKg = result.getFloat("tong_kg");
                LocalDateTime createdAtFormat = createdAt.toLocalDateTime();
                DeliveryBillDTO deliveryBill = new DeliveryBillDTO(id, name, nameEmployee, totalKg, createdAtFormat);
                list_deliveryBills.add(deliveryBill);
            }
        } catch (Exception e) {
            return null;
        }
        return list_deliveryBills;
    }

    public static ArrayList<DeliveryBillDTO> searchDeliveryBillByAmount(int startAmount,
            int endAmount) {
        ArrayList<DeliveryBillDTO> list_deliveryBills = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "PhieuXuat.id, " +
                    "ten_phieu_xuat, " +
                    "PhieuXuat.createdAt, " +
                    "NhanVien.ho_va_ten, " +
                    "SUM(ChiTietPhieuXuat.so_luong) AS tong_kg " +
                    "FROM PhieuXuat " +
                    "JOIN ChiTietPhieuXuat ON PhieuXuat.id = ChiTietPhieuXuat.PhieuXuat_id " +
                    "JOIN TaiKhoan ON  TaiKhoan.id = PhieuXuat.TaiKhoan_id " +
                    "JOIN NhanVien ON TaiKhoan.id = NhanVien.TaiKhoan_id " +
                    "WHERE PhieuXuat.is_active = 1 and " +
                    " (select sum(so_luong) from ChiTietPhieuXuat where ChiTietPhieuXuat.PhieuXuat_id = PhieuXuat.id) between ? and ? "
                    +
                    "GROUP BY PhieuXuat.id";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, startAmount);
            pst.setInt(2, endAmount);
            ResultSet result = pst.executeQuery();
            list_deliveryBills = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("ten_phieu_xuat");
                Timestamp createdAt = result.getTimestamp("createdAt");
                String nameEmployee = result.getString("ho_va_ten");
                float totalKg = result.getFloat("tong_kg");
                LocalDateTime createdAtFormat = createdAt.toLocalDateTime();
                DeliveryBillDTO deliveryBill = new DeliveryBillDTO(id, name, nameEmployee, totalKg, createdAtFormat);
                list_deliveryBills.add(deliveryBill);
            }
        } catch (Exception e) {
            return null;
        }
        return list_deliveryBills;
    }

    public static ArrayList<DeliveryBillDTO> searchDeliveryBillByAmountAndDate(String startTime, String endTime,
            int startAmount,
            int endAmount) {
        ArrayList<DeliveryBillDTO> list_deliveryBills = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "PhieuXuat.id, " +
                    "ten_phieu_xuat, " +
                    "PhieuXuat.createdAt, " +
                    "NhanVien.ho_va_ten, " +
                    "SUM(ChiTietPhieuXuat.so_luong) AS tong_kg " +
                    "FROM PhieuXuat " +
                    "JOIN ChiTietPhieuXuat ON PhieuXuat.id = ChiTietPhieuXuat.PhieuXuat_id " +
                    "JOIN TaiKhoan ON  TaiKhoan.id = PhieuXuat.TaiKhoan_id " +
                    "JOIN NhanVien ON TaiKhoan.id = NhanVien.TaiKhoan_id " +
                    "WHERE PhieuXuat.is_active = 1 " +
                    " and PhieuXuat.createdAt between ? and ? " +
                    " and (select sum(so_luong) from ChiTietPhieuXuat where ChiTietPhieuXuat.PhieuXuat_id = PhieuXuat.id) between ? and ? "
                    +
                    "GROUP BY PhieuXuat.id";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startTime);
            pst.setString(2, endTime);
            pst.setInt(3, startAmount);
            pst.setInt(4, endAmount);
            ResultSet result = pst.executeQuery();
            list_deliveryBills = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("ten_phieu_xuat");
                Timestamp createdAt = result.getTimestamp("createdAt");
                String nameEmployee = result.getString("ho_va_ten");
                float totalKg = result.getFloat("tong_kg");
                LocalDateTime createdAtFormat = createdAt.toLocalDateTime();
                DeliveryBillDTO deliveryBill = new DeliveryBillDTO(id, name, nameEmployee, totalKg, createdAtFormat);
                list_deliveryBills.add(deliveryBill);
            }
        } catch (Exception e) {
            return null;
        }
        return list_deliveryBills;
    }

    // removeDeliveryBill
    public static boolean removeDeliveryBill(int deliveryBillId, ArrayList<DetailDeliveryBillDTO> list_detail) {
        Connection conn = null;
        try {
            conn = mysqlConnect.getConnection();
            String sql = "UPDATE PhieuXuat SET is_active = 0 WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, deliveryBillId);
            int affectedRow = pst.executeUpdate();
            if (affectedRow > 0) {

                for (DetailDeliveryBillDTO detailDeliveryBillDTO : list_detail) {
                    EnterCouponDAO.updateQuantityIngredient(conn, detailDeliveryBillDTO.getIngradientId(),
                            detailDeliveryBillDTO.getQuantity());

                }

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<DeliveryBillDTO> getDeliveryl = getDeliveryBill();

        for (DeliveryBillDTO deliveryBillDTO : getDeliveryl) {
            System.out.println(deliveryBillDTO.getId());
        }
    }

}
