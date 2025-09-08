package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.project.DTO.DetailEnterCouponDTO;
import com.project.DTO.EnterCouponDTO;

public class EnterCouponDAO {

    public static Boolean createEnterCoupon(EnterCouponDTO enterCoupon, ArrayList<DetailEnterCouponDTO> new_detail) {
        Connection conn = null;
        try {
            conn = mysqlConnect.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO PhieuNhap(ten_phieu_nhap, TaiKhoan_id, NhaCungCap_id) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, enterCoupon.getNameEnterCoupon());
            pst.setInt(2, enterCoupon.getEmployee_id());
            pst.setInt(3, enterCoupon.getSupplier_id());

            int affectedRow = pst.executeUpdate();
            int enterCouponId = -1;
            if (affectedRow > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    enterCouponId = generatedKeys.getInt(1);
                    for (DetailEnterCouponDTO detailDTO : new_detail) {
                        String sql2 = "INSERT INTO ChiTietPhieuNhap (PhieuNhap_id, NguyenLieu_id, so_luong, don_gia) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pst2 = conn.prepareStatement(sql2)) {
                            pst2.setInt(1, enterCouponId);
                            pst2.setInt(2, detailDTO.getIngredient_id());
                            pst2.setInt(3, detailDTO.getQuantity());
                            pst2.setFloat(4, (float) detailDTO.getPrice());
                            pst2.executeUpdate();
                        }

                        updateQuantityIngredient(conn, detailDTO.getIngredient_id(), detailDTO.getQuantity());

                    }
                }
            }

            // Commit transaction
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean updateQuantityIngredient(Connection conn, int id, int quantity) throws SQLException {
        String sql = "SELECT so_luong FROM NguyenLieu WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int oldQuantity = rs.getInt("so_luong");
                    int newQuantity = oldQuantity + quantity;

                    String updateSql = "UPDATE NguyenLieu SET so_luong = ? WHERE id = ?";
                    try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                        updatePst.setInt(1, newQuantity);
                        updatePst.setInt(2, id);
                        updatePst.executeUpdate();
                    }

                    return true;
                }
            }
        }
        // Không tìm thấy nguyên liệu với id tương ứng
        return false;
    }

    public static ArrayList<EnterCouponDTO> getAllEnterCoupon() {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 " +
                    "GROUP BY pn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static ArrayList<EnterCouponDTO> SearchEnterCouponByName(String keyword) {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 and ten_phieu_nhap like ?" +
                    "GROUP BY pn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, keyword + "%");
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static ArrayList<EnterCouponDTO> SearchEnterCouponBySupplier(String keyword) {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 and ten_ncc like ?" +
                    "GROUP BY pn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, keyword + "%");
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static ArrayList<EnterCouponDTO> SearchEnterCouponByEmployee(String keyword) {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 and ho_va_ten like ?" +
                    "GROUP BY pn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, keyword + "%");
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static ArrayList<DetailEnterCouponDTO> getDetailEnterCoupon(int idEnterCoupon) {
        ArrayList<DetailEnterCouponDTO> list_detail = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "ctpn.id, " +
                    "ten_NL, " +
                    "nl.id as nl_id, " +
                    "don_gia, " +
                    "ctpn.so_luong, " +
                    "SUM(ctpn.so_luong * don_gia) AS tong_tien " +
                    "FROM ChiTietPhieuNhap ctpn " +
                    "JOIN NguyenLieu nl ON nl.id = ctpn.NguyenLieu_id " +
                    "WHERE PhieuNhap_id = ? " +
                    "GROUP BY ctpn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idEnterCoupon);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameIngredient = result.getString("ten_NL");
                float nameEmployee = result.getFloat("don_gia");
                int quantity = result.getInt("so_luong");
                int ingredientId = result.getInt("nl_id");
                float totalValues = result.getFloat("tong_tien");
                list_detail.add(new DetailEnterCouponDTO(id, nameIngredient, nameEmployee, quantity, totalValues,
                        ingredientId));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_detail;
    }

    // search advanced

    public static ArrayList<EnterCouponDTO> searchEnterCouponByDay(String startTime, String endTime) {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 " +
                    "AND pn.createdAt BETWEEN ? AND  ? " +
                    "GROUP BY pn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startTime);
            pst.setString(2, endTime);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static ArrayList<EnterCouponDTO> searchEnterCouponByTotalReceipt(int startTotalReceipt,
            int endTotalReceipt) {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 " +
                    "AND (SELECT SUM(so_luong * don_gia) FROM ChiTietPhieuNhap WHERE PhieuNhap_id = pn.id) BETWEEN ? AND ? "
                    +
                    "GROUP BY pn.id ";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, startTotalReceipt);
            pst.setInt(2, endTotalReceipt);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static ArrayList<EnterCouponDTO> searchEnterCouponByTotalReceiptAndDate(String startTime, String endTime,
            int startTotalReceipt, int endTotalReceipt) {
        ArrayList<EnterCouponDTO> list_EnterCoupon = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "pn.id, " +
                    "ten_phieu_nhap, " +
                    "ho_va_ten, " +
                    "ten_ncc, " +
                    "SUM(distinct ctpn.so_luong * ctpn.don_gia) AS total, " +
                    "pn.createdAt " +
                    "FROM PhieuNhap pn " +
                    "left JOIN ChiTietPhieuNhap ctpn ON pn.id = ctpn.PhieuNhap_id " +
                    "JOIN NhaCungCap ON pn.NhaCungCap_id = NhaCungCap.id " +
                    "JOIN TaiKhoan ON TaiKhoan.id = pn.TaiKhoan_id " +
                    "left JOIN NhanVien ON NhanVien.TaiKhoan_id = TaiKhoan.id " +
                    "Where pn.is_active = 1 " +
                    "AND pn.createdAt BETWEEN ?  AND  ? " +
                    "AND (SELECT SUM(so_luong * don_gia) FROM ChiTietPhieuNhap WHERE PhieuNhap_id = pn.id) BETWEEN ? AND ? "
                    +
                    "GROUP BY pn.id";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, startTime);
            pst.setString(2, endTime);
            pst.setInt(3, startTotalReceipt);
            pst.setInt(4, endTotalReceipt);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nameEnterCoupon = result.getString("ten_phieu_nhap");
                String nameEmployee = result.getString("ho_va_ten");
                String nameSupplier = result.getString("ten_ncc");
                float totalValues = result.getFloat("total");
                Timestamp createdAt = result.getTimestamp("createdAt");
                LocalDateTime formateCreatedAt = createdAt.toLocalDateTime();
                list_EnterCoupon.add(new EnterCouponDTO(id, nameEnterCoupon, nameEmployee, nameSupplier, totalValues,
                        formateCreatedAt));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_EnterCoupon;
    }

    public static boolean removeEnterCoupon(int id, ArrayList<DetailEnterCouponDTO> list_detail) {
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "UPDATE PhieuNhap SET is_active = 0 WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int affectedRow = pst.executeUpdate();
            if (affectedRow > 0) {
                for (DetailEnterCouponDTO detailEnterCouponDTO : list_detail) {
                    DeliveryBillDAO.updateQuantityIngredient(conn, detailEnterCouponDTO.getIngredient_id(),
                            detailEnterCouponDTO.getQuantity());
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
        ArrayList<DetailEnterCouponDTO> list_EnterCoupon = getDetailEnterCoupon(1);

        for (DetailEnterCouponDTO enterCouponDTO : list_EnterCoupon) {
            System.out.println(enterCouponDTO.getId());
            System.out.println(enterCouponDTO.getNameIngredient());
            System.out.println(enterCouponDTO.getPrice());
        }
    }
}
