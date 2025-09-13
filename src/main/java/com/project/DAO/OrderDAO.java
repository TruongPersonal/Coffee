package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.project.DTO.OrderDTO;
import com.project.DTO.OrderDetailDTO;

public class OrderDAO {

    public ArrayList<OrderDTO> getAll() {
        ArrayList<OrderDTO> orderDTOs = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from HoaDon";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String order_status = rs.getString("trang_thai");
                int paymentMethod_id = rs.getInt("PTThanhToan_id");
                int acount_id = rs.getInt("TaiKhoan_id");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                orderDTOs.add(new OrderDTO(id, order_status, createdAt, paymentMethod_id, acount_id));
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDTOs;
    }

    public boolean updateStatus(int orderID, String status) {
        boolean rs = false;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Update HoaDon Set trang_thai = ? Where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(2, orderID);
            pst.setString(1, status);
            int rowAffected = pst.executeUpdate();

            if (rowAffected > 0) {
                rs = true;
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotal() {
        LinkedHashMap<OrderDTO, Float> orderDTOs = new LinkedHashMap<OrderDTO, Float>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT HD.*, SUM(gia * CTHD.so_luong) as tong FROM HoaDon HD, ChiTietHoaDon CTHD, SanPham SP\r\n"
                    + //
                    "WHERE HD.id = CTHD.HoaDon_id AND SP.id = CTHD.SanPham_id\r\n" + //
                    "GROUP BY HD.id ORDER BY HD.createdAt DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String order_status = rs.getString("trang_thai");
                int paymentMethod_id = rs.getInt("PTThanhToan_id");
                int acount_id = rs.getInt("TaiKhoan_id");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                OrderDTO orderDTO = new OrderDTO(id, order_status, createdAt, paymentMethod_id, acount_id);
                Float total = rs.getFloat("tong");
                orderDTOs.put(orderDTO, total);
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotalByOrderID(int orderID) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = new LinkedHashMap<OrderDTO, Float>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT HD.*, SUM(gia * CTHD.so_luong) as tong FROM HoaDon HD, ChiTietHoaDon CTHD, SanPham SP\r\n"
                    +
                    "WHERE HD.id = CTHD.HoaDon_id AND SP.id = CTHD.SanPham_id AND HD.id like ?\r\n" +
                    "GROUP BY HD.id ORDER BY HD.createdAt DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, orderID + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String order_status = rs.getString("trang_thai");
                int paymentMethod_id = rs.getInt("PTThanhToan_id");
                int acount_id = rs.getInt("TaiKhoan_id");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                OrderDTO orderDTO = new OrderDTO(id, order_status, createdAt, paymentMethod_id, acount_id);
                Float total = rs.getFloat("tong");
                orderDTOs.put(orderDTO, total);
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotalByAccountID(int accountID) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = new LinkedHashMap<OrderDTO, Float>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT HD.*, SUM(gia * CTHD.so_luong) as tong FROM HoaDon HD, ChiTietHoaDon CTHD, SanPham SP\r\n"
                    +
                    "WHERE HD.id = CTHD.HoaDon_id AND SP.id = CTHD.SanPham_id AND HD.TaiKhoan_id like ?\r\n" +
                    "GROUP BY HD.id ORDER BY HD.createdAt DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, accountID + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String order_status = rs.getString("trang_thai");
                int paymentMethod_id = rs.getInt("PTThanhToan_id");
                int acount_id = rs.getInt("TaiKhoan_id");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                OrderDTO orderDTO = new OrderDTO(id, order_status, createdAt, paymentMethod_id, acount_id);
                Float total = rs.getFloat("tong");
                orderDTOs.put(orderDTO, total);
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDTOs;
    }


    public LinkedHashMap<OrderDTO, Float> getAllWithTotalByEmp_Name(String empName) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = new LinkedHashMap<OrderDTO, Float>();
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT HD.id, HD.trang_thai, HD.PTThanhToan_id, HD.TaiKhoan_id, HD.createdAt, SUM(SP.gia * CTHD.so_luong) as tong "
                    +
                    "FROM HoaDon HD " +
                    "INNER JOIN ChiTietHoaDon CTHD ON HD.id = CTHD.HoaDon_id " +
                    "INNER JOIN SanPham SP ON CTHD.SanPham_id = SP.id " +
                    "INNER JOIN NhanVien NV ON HD.TaiKhoan_id = NV.TaiKhoan_id " +
                    "WHERE NV.ho_va_ten LIKE ? " +
                    "GROUP BY HD.id " +
                    "ORDER BY HD.createdAt DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + empName + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String order_status = rs.getString("trang_thai");
                int paymentMethod_id = rs.getInt("PTThanhToan_id");
                int acount_id = rs.getInt("TaiKhoan_id");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                OrderDTO orderDTO = new OrderDTO(id, order_status, createdAt, paymentMethod_id, acount_id);
                Float total = rs.getFloat("tong");
                orderDTOs.put(orderDTO, total);
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotal(String orderStatus, int pmMethod_id, Timestamp startDate,
            Timestamp endDate, Float startTotal, Float endTotal) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = new LinkedHashMap<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            StringBuilder sqlBuilder = new StringBuilder(
                    "SELECT HD.*, SUM(gia * CTHD.so_luong) as tong FROM HoaDon HD, ChiTietHoaDon CTHD, SanPham SP\r\n"
                            +
                            "WHERE HD.id = CTHD.HoaDon_id AND SP.id = CTHD.SanPham_id\r\n");

            if (!orderStatus.equals("Tất cả")) {
                sqlBuilder.append(" AND HD.trang_thai like ?");
            }

            if (pmMethod_id != 0) {
                sqlBuilder.append(" AND HD.PTThanhToan_id = ?");
            }

            if (startDate != null && endDate != null) {
                sqlBuilder.append(" AND HD.createdAt BETWEEN ? AND ?");
            }

            sqlBuilder.append(" GROUP BY HD.id ");

            if (startTotal != -1 && endTotal != -1) {
                sqlBuilder.append(" HAVING tong >= ? AND tong <= ?");
            }

            sqlBuilder.append(" ORDER BY HD.createdAt DESC");

            PreparedStatement pst = conn.prepareStatement(sqlBuilder.toString());
            int parameterIndex = 1;

            if (!orderStatus.equals("Tất cả")) {
                pst.setString(parameterIndex++, orderStatus);
            }

            if (pmMethod_id != 0) {
                pst.setInt(parameterIndex++, pmMethod_id);
            }

            if (startDate != null && endDate != null) {
                pst.setTimestamp(parameterIndex++, startDate);
                pst.setTimestamp(parameterIndex++, endDate);
            }

            if (startTotal != -1 && endTotal != -1) {
                pst.setFloat(parameterIndex++, startTotal);
                pst.setFloat(parameterIndex++, endTotal);
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String order_status = rs.getString("trang_thai");
                int paymentMethod_id = rs.getInt("PTThanhToan_id");
                int acount_id = rs.getInt("TaiKhoan_id");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                OrderDTO orderDTO = new OrderDTO(id, order_status, createdAt, paymentMethod_id, acount_id);
                Float total = rs.getFloat("tong");
                orderDTOs.put(orderDTO, total);
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDTOs;
    }


    public boolean addOrder(OrderDTO order, ArrayList<OrderDetailDTO> orderDetails) {
        Connection conn = mysqlConnect.getConnection();
        try {
            conn.setAutoCommit(false);

            String sql = "Insert into HoaDon (trang_thai, PTThanhToan_id, TaiKhoan_id) Values(?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, order.getOrder_status());
            pst.setInt(2, order.getPaymentMethod_id());
            pst.setInt(3, order.getAcount_id());
            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int order_id = generatedKeys.getInt(1);
                    for (OrderDetailDTO o : orderDetails) {
                        sql = "Insert into ChiTietHoaDon (so_luong, SanPham_id, HoaDon_id) Values(?, ?, ?)";
                        pst = conn.prepareStatement(sql);
                        pst.setInt(1, o.getQuantity());
                        pst.setInt(2, o.getProduct_id());
                        pst.setInt(3, order_id);
                        pst.executeUpdate();
                    }
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.commit();
            conn.setAutoCommit(true);
            mysqlConnect.closeConnection(conn);
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                mysqlConnect.closeConnection(conn);
                e1.printStackTrace();
            }
        }
        return false;
    }
}
