package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.project.DTO.CategoryDTO;

public class CategoryDAO {

    public ArrayList<CategoryDTO> getAll() {
        ArrayList<CategoryDTO> categories = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from LoaiSanPham";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            categories = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String category_name = rs.getString("ten_loai");
                int is_active = rs.getInt("is_active");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();

                categories.add(new CategoryDTO(id, category_name, is_active, createdAt));
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public CategoryDTO getCategoryByID(int search_id) {
        CategoryDTO category = null;

        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from LoaiSanPham Where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, search_id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String category_name = rs.getString("ten_loai");
                int is_active = rs.getInt("is_active");
                LocalDateTime createdAt = rs.getTimestamp("createAt").toLocalDateTime();

                category = new CategoryDTO(id, category_name, is_active, createdAt);
            }

            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

}
