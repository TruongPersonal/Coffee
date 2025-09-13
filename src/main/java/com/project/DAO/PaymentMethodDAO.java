package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.DTO.PaymentMethodDTO;

public class PaymentMethodDAO {
    public ArrayList<PaymentMethodDTO> getAll() {
        ArrayList<PaymentMethodDTO> rs = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from PTThanhToan";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            rs = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String payment_name = result.getString("ten_PT");
                String description = result.getString("mo_ta");
                int is_active = result.getInt("is_active");
                String created_date = result.getString("createdAt");
                rs.add(new PaymentMethodDTO(id, payment_name, description, is_active, created_date));
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public PaymentMethodDTO getByID(int paymentmethod_id) {
        PaymentMethodDTO rs = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from PTThanhToan Where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, paymentmethod_id);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String payment_name = result.getString("ten_PT");
                rs = new PaymentMethodDTO(id, payment_name);
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
