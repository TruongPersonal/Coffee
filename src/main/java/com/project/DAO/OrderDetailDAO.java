package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.DTO.OrderDetailDTO;

public class OrderDetailDAO {
    public ArrayList<OrderDetailDTO> getByOrderID(int orderID) {
        ArrayList<OrderDetailDTO> orderDetails = null;

        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from ChiTietHoaDon Where HoaDon_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderID);
            ResultSet rs = pst.executeQuery();
            orderDetails = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("so_luong");
                int product_id = rs.getInt("SanPham_id");
                int order_id = rs.getInt("HoaDon_id");
                orderDetails.add(new OrderDetailDTO(id, quantity, product_id, order_id));
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }

        return orderDetails;
    }
}
