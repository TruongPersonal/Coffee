
package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.DTO.WareHouse;
import com.project.DTO.WareHouseDTO;

public class WareHouseDAO {

    public static ArrayList<WareHouseDTO> get_all_ingredients() {
        ArrayList<WareHouseDTO> list_ingredient = new ArrayList<WareHouseDTO>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from NguyenLieu where is_active = 1";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("ten_NL");
                int quantity = rs.getInt("so_luong");
                list_ingredient.add(new WareHouseDTO(id, name, quantity));
            }

        } catch (Exception e) {
            return null;
        }
        return list_ingredient;
    }

    public List<WareHouse> getAllWareHouse() throws Exception {
        Connection connect = mysqlConnect.getConnection();
        List<WareHouse> warehouses = new ArrayList<WareHouse>();
        String sql = "SELECT * FROM NguyenLieu WHERE is_active = 1";
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            WareHouse warehouse = new WareHouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setName(rs.getString("ten_NL"));
            warehouse.setUnit(rs.getString("don_vi"));
            warehouse.setQuantity(rs.getInt("so_luong"));
            warehouse.setISAcctive(rs.getInt("is_active"));
            warehouse.setCreateDate(rs.getTimestamp("created_date"));
            warehouse.setUpdateDate(rs.getTimestamp("updatedAt"));
            warehouses.add(warehouse);
        }
        return warehouses;
    }

    public boolean AddWareHouse(WareHouse warehouse) throws Exception {
        Connection connection = mysqlConnect.getConnection();
        String sql = "INSERT INTO NguyenLieu(ten_NL, don_vi, so_luong) VALUES(?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, warehouse.getName());
        ps.setString(2, warehouse.getUnit());
        ps.setInt(3, warehouse.getQuantity());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void UpdateWareHouse(WareHouse warehouse) throws Exception {
        Connection connection = mysqlConnect.getConnection();
        String sql = "UPDATE NguyenLieu SET so_luong = ?, updatedAt = NOW() WHERE ten_NL = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouse.getQuantity());
        ps.setString(2, warehouse.getName());
        ps.executeUpdate();
    }

    public static Boolean decreaseIngredientWarehouse(int id_ingredient, int quantity) throws Exception {
        try {
            Connection connection = mysqlConnect.getConnection();
            String sql = "UPDATE NguyenLieu SET so_luong = so_luong - ?, updatedAt = NOW() WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, id_ingredient);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public WareHouse getNameWareHouse(String name) throws Exception {
        Connection connection = mysqlConnect.getConnection();
        String sql = "SELECT * FROM NguyenLieu WHERE ten_NL = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            WareHouse warehouse = new WareHouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setName(rs.getString("ten_NL"));
            warehouse.setUnit(rs.getString("don_vi"));
            warehouse.setQuantity(rs.getInt("so_luong"));
            return warehouse;
        }
        return null;
    }

    public void deleteWareHouse(int id) throws SQLException {
        Connection connection = mysqlConnect.getConnection();
        String sql = "UPDATE NguyenLieu SET is_active = 0 WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<WareHouse> searchAllWareHouseById(int id) throws Exception {
        List<WareHouse> warehouses = new ArrayList<WareHouse>();
        Connection connection = mysqlConnect.getConnection();
        String sql = "SELECT * FROM NguyenLieu WHERE is_active = 1 AND id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            WareHouse warehouse = new WareHouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setName(rs.getString("ten_NL"));
            warehouse.setUnit(rs.getString("don_vi"));
            warehouse.setQuantity(rs.getInt("so_luong"));
            warehouse.setISAcctive(rs.getInt("is_active"));
            warehouse.setCreateDate(rs.getTimestamp("created_date"));
            warehouse.setUpdateDate(rs.getTimestamp("updatedAt"));
            warehouses.add(warehouse);
        }
        return warehouses;
    }

    public List<WareHouse> searchAllWareHouseByName(String name) throws Exception {
        List<WareHouse> warehouses = new ArrayList<WareHouse>();
        Connection connection = mysqlConnect.getConnection();
        String sql = "SELECT * FROM NguyenLieu WHERE is_active = 1 AND ten_NL LIKE '%" + name + "%' ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            WareHouse warehouse = new WareHouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setName(rs.getString("ten_NL"));
            warehouse.setUnit(rs.getString("don_vi"));
            warehouse.setQuantity(rs.getInt("so_luong"));
            warehouse.setISAcctive(rs.getInt("is_active"));
            warehouse.setCreateDate(rs.getTimestamp("created_date"));
            warehouse.setUpdateDate(rs.getTimestamp("updatedAt"));
            warehouses.add(warehouse);
        }
        return warehouses;
    }

    public WareHouse getIdWareHouse(int id) throws Exception {
        Connection connection = mysqlConnect.getConnection();
        String sql = "SELECT * FROM NguyenLieu WHERE id = ? AND is_active = 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            WareHouse warehouse = new WareHouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setName(rs.getString("ten_NL"));
            warehouse.setUnit(rs.getString("don_vi"));
            warehouse.setQuantity(rs.getInt("so_luong"));
            warehouse.setISAcctive(rs.getInt("is_active"));
            warehouse.setCreateDate(rs.getTimestamp("created_date"));
            warehouse.setUpdateDate(rs.getTimestamp("updatedAt"));
            return warehouse;
        }
        return null;
    }

    public void updateNameWareHouse(WareHouse warehouse) throws Exception {
        Connection connection = mysqlConnect.getConnection();
        String sql = "UPDATE NguyenLieu SET ten_NL = ?, updatedAt = NOW() WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, warehouse.getName());
        ps.setInt(2, warehouse.getId());
        ps.executeUpdate();
    }

    public boolean WareHouseExist(String name) {
        boolean exist = false;
        try {
            Connection connection = mysqlConnect.getConnection();
            String sql = "SELECT COUNT(*) FROM NguyenLieu WHERE ten_NL = ? and is_active = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}
