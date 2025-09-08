package com.project.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.swing.JOptionPane;

import com.project.DTO.DetailRecipeDTO;
import com.project.DTO.RecipeDTO;

public class RecipeDAO {

    public static int createRecipe(RecipeDTO newRecipe) {
        int generatedId = -1; // Default value indicating failure
        try (
                Connection conn = mysqlConnect.getConnection();
                // Use Statement.RETURN_GENERATED_KEYS to retrieve the auto-generated keys
                PreparedStatement pst = conn.prepareStatement(
                        "INSERT INTO CongThuc (ten_cong_thuc, mo_ta) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, newRecipe.getName_recipe());
            pst.setString(2, newRecipe.getDescription());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated key
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1); // Get the generated ID
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
            if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                JOptionPane.showMessageDialog(null, "Tên công thức đã tồn tại");
                return -1; // Return -1 to indicate failure due to duplicate entry
            }
            e.printStackTrace();
        }

        return generatedId;
    }

    // create detail recipe
    public static void createDetailRecipe(DetailRecipeDTO detailRecipe) {
        try (
                Connection conn = mysqlConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(
                        "INSERT INTO CongThucSanPham (SanPham_id, NguyenLieu_id, CongThuc_id,  don_vi, so_luong) VALUES (?,?,?,?,?)")) {
            // pst.setInt(1, detailRecipe.getId_product());
            // pst.setInt(2, detailRecipe.getId_ingredient());
            // pst.setInt(3, detailRecipe.getId_recipe());
            // pst.setString(4, detailRecipe.getUnit());
            // pst.setInt(5, detailRecipe.getQuantity());
            pst.setInt(1, detailRecipe.getId_product());
            pst.setInt(2, detailRecipe.getId_ingredient());
            pst.setInt(3, detailRecipe.getId_recipe());
            pst.setString(4, detailRecipe.getUnit());
            pst.setInt(5, detailRecipe.getQuantity());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<RecipeDTO> get_all_recipe() {
        ArrayList<RecipeDTO> list_recipe = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "select * from CongThuc where is_active = ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, 1);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name_recipe = result_query.getString("ten_cong_thuc");
                String description = result_query.getString("mo_ta");
                Boolean is_active = result_query.getBoolean("is_active");
                Timestamp createdAt = result_query.getTimestamp("createdAt");
                LocalDateTime createdAtFormated = createdAt.toLocalDateTime();

                list_recipe.add(new RecipeDTO(id, name_recipe, description, is_active, createdAtFormated));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe;

    }

    public static Boolean updateSupplier(RecipeDTO updateRecipe) {
        Boolean success = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "UPDATE CongThuc SET ten_cong_thuc = ?, mo_ta = ?, is_active = ? updatedAt = NOW() WHERE id = ?;";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, updateRecipe.getName_recipe());
            pst.setString(2, updateRecipe.getDescription());
            pst.setBoolean(3, updateRecipe.getIs_active());
            pst.setInt(5, updateRecipe.getId());

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

    public static Boolean ChangeIsActiveRecipe(int id, Boolean is_active) {
        Boolean success = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "UPDATE CongThuc SET is_active = ? WHERE id = ?;";
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

    public static ArrayList<RecipeDTO> SearchRecipeByName(String search_recipe) {
        ArrayList<RecipeDTO> list_recipe = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "select * from CongThuc where ten_cong_thuc  like ? and is_active = 1";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, search_recipe + "%");

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name_recipe = result_query.getString("ten_cong_thuc");
                String description = result_query.getString("mo_ta");
                Boolean is_active = result_query.getBoolean("is_active");
                Timestamp createdAt = result_query.getTimestamp("createdAt");
                LocalDateTime createdAtFormated = createdAt.toLocalDateTime();

                list_recipe.add(new RecipeDTO(id, name_recipe, description, is_active, createdAtFormated));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe;
    }

    public static ArrayList<DetailRecipeDTO> getDetailRecipeByIdProduct(int id_product) {
        ArrayList<DetailRecipeDTO> list_recipe = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT " +

                    "SanPham.id AS SanPham_id, " +
                    "NguyenLieu.ten_NL AS ten_NL, " +
                    "CongThucSanPham.don_vi AS don_vi, " +
                    "CongThucSanPham.so_luong AS so_luong " +
                    "FROM CongThucSanPham " +
                    "JOIN SanPham ON CongThucSanPham.SanPham_id = SanPham.id " +
                    "JOIN NguyenLieu ON CongThucSanPham.NguyenLieu_id = NguyenLieu.id " +
                    "JOIN CongThuc ON CongThucSanPham.CongThuc_id = CongThuc.id " +
                    "WHERE SanPham.id = ? and CongThuc.is_active = ? " +
                    "GROUP BY CongThuc.ten_cong_thuc, " +
                    "CongThuc.mo_ta, " +
                    "SanPham.ten_SP, " +
                    "NguyenLieu.ten_NL, " +
                    "CongThucSanPham.id, " +
                    "CongThucSanPham.so_luong";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id_product);
            pst.setInt(2, 1);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_ingredient = result_query.getString("ten_NL");
                String unit = result_query.getString("don_vi");
                int quantity = result_query.getInt("so_luong");
                list_recipe.add(new DetailRecipeDTO(name_ingredient, unit, quantity));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe;
    }

    public static ArrayList<DetailRecipeDTO> getDetailRecipeById(int id_recipe) {
        ArrayList<DetailRecipeDTO> list_recipe = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT " +
                    "CongThucSanPham.id AS CongThucSanPham_id, " +
                    "SanPham.ten_SP AS ten_SP, " +
                    "SanPham.id AS SanPham_id, " +
                    "SanPham.so_luong AS so_luong " +
                    "FROM CongThucSanPham " +
                    "JOIN SanPham ON CongThucSanPham.SanPham_id = SanPham.id " +
                    "JOIN NguyenLieu ON CongThucSanPham.NguyenLieu_id = NguyenLieu.id " +
                    "JOIN CongThuc ON CongThucSanPham.CongThuc_id = CongThuc.id " +
                    "WHERE CongThuc.id = ? and CongThuc.is_active = ? " +
                    "GROUP BY CongThuc.ten_cong_thuc, " +
                    "CongThuc.mo_ta, " +
                    "SanPham.ten_SP, " +
                    "NguyenLieu.ten_NL, " +
                    "CongThucSanPham.id, " +
                    "CongThucSanPham.so_luong";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id_recipe);
            pst.setInt(2, 1);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int ProductRecipeId = result_query.getInt("CongThucSanPham_id");
                String name_product = result_query.getString("ten_SP");
                int product_id = result_query.getInt("SanPham_id");
                int quantity = result_query.getInt("so_luong");
                list_recipe.add(new DetailRecipeDTO(ProductRecipeId, product_id, quantity));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe;
    }

    public static ArrayList<RecipeDTO> getRecipeById(int id_recipe) {
        ArrayList<RecipeDTO> list_recipe = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT "
                    + "CongThuc.ten_cong_thuc as ten_cong_thuc  , "
                    + "CongThuc.mo_ta as mo_ta, "
                    + "SanPham.ten_SP as ten_SP, "
                    + "NguyenLieu.ten_NL as ten_NL, "
                    + "CongThucSanPham.so_luong as so_luong, "
                    + "CongThucSanPham.don_vi as don_vi "
                    + "FROM CongThucSanPham "
                    + "JOIN SanPham ON CongThucSanPham.SanPham_id = SanPham.id "
                    + "JOIN NguyenLieu ON CongThucSanPham.NguyenLieu_id = NguyenLieu.id "
                    + "JOIN CongThuc ON CongThucSanPham.CongThuc_id = CongThuc.id "
                    + "WHERE CongThuc.id = ? and CongThuc.is_active = ? "
                    + "GROUP BY CongThuc.ten_cong_thuc, CongThuc.mo_ta, SanPham.ten_SP, NguyenLieu.ten_NL, CongThucSanPham.so_luong, CongThucSanPham.don_vi;";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id_recipe);
            pst.setInt(2, 1);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                String name_recipe = result_query.getString("ten_cong_thuc");
                String description = result_query.getString("mo_ta");
                String name_product = result_query.getString("ten_SP");
                String name_ingredient = result_query.getString("ten_NL");
                int quantity = result_query.getInt("so_luong");
                String unit = result_query.getString("don_vi");
                DetailRecipeDTO detail_ingredient = new DetailRecipeDTO(name_product, name_ingredient, quantity, unit);
                list_recipe.add(new RecipeDTO(name_recipe, description, detail_ingredient));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe;
    }

    public static boolean updateDetailRecipe(DetailRecipeDTO data) {
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "UPDATE CongThucSanPham SET so_luong = ? WHERE SanPham_id = ? and NguyenLieu_id = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, data.getQuantity());
            pst.setInt(2, data.getId_product());
            pst.setInt(3, data.getId_ingredient());

            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // delete soft table SanPhamCongThuc
    public static boolean deleteDetailRecipe(int id_recipe) {
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "UPDATE CongThucSanPham SET is_active = 0 WHERE id = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id_recipe);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // get recipe by SanPham_id
    public static ArrayList<DetailRecipeDTO> getRecipeByProductId(int id_product) {
        ArrayList<DetailRecipeDTO> list_recipe_detail = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT * FROM CongThucSanPham WHERE SanPham_id = ? and is_active = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id_product);
            pst.setInt(2, 1);
            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                int id = result_query.getInt("id");
                int product_id = result_query.getInt("SanPham_id");
                int recipe_id = result_query.getInt("CongThuc_id");
                int ingredient_id = result_query.getInt("NguyenLieu_id");
                String unit = result_query.getString("don_vi");
                int quantity = result_query.getInt("so_luong");
                DetailRecipeDTO detail_ingredient = new DetailRecipeDTO(id, product_id, ingredient_id, recipe_id, unit,
                        quantity);
                list_recipe_detail.add(detail_ingredient);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe_detail;
    }

    // get recipe by ingredient id
    public static ArrayList<DetailRecipeDTO> getRecipeByIngredientId(int id_ingredient) {
        ArrayList<DetailRecipeDTO> list_recipe_detail = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT * FROM CongThucSanPham WHERE NguyenLieu_id = ? and is_active = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id_ingredient);
            pst.setInt(2, 1);
            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                int id = result_query.getInt("id");
                int product_id = result_query.getInt("SanPham_id");
                int recipe_id = result_query.getInt("CongThuc_id");
                int ingredient_id = result_query.getInt("NguyenLieu_id");
                String unit = result_query.getString("don_vi");
                int quantity = result_query.getInt("so_luong");
                DetailRecipeDTO detail_ingredient = new DetailRecipeDTO(id, product_id, ingredient_id, recipe_id, unit,
                        quantity);
                list_recipe_detail.add(detail_ingredient);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list_recipe_detail;
    }

    public static void main(String[] args) {
        // demo function createRecipe()
        int id = createRecipe(new RecipeDTO("ádfaádfa sdfsdf", "ádasd"));
        System.out.println(id);

    }

}
