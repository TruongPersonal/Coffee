package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.DTO.User;

public class UserDao {
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM NhanVien where is_active = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1); // is_active = 1
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("ho_va_ten"));
                user.setDate(rs.getString("ngay_sinh"));
                user.setAddress(rs.getString("dia_chi"));
                user.setPosition(rs.getString("chuc_vu"));
                user.setPhone(rs.getString("sdt"));
                user.setSalary(rs.getFloat("luong"));
                user.setDateCreate(rs.getTimestamp("createdAt"));
                user.setAccountId(rs.getInt("TaiKhoan_id"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(User user) {
        boolean rs = false;
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "INSERT INTO NhanVien( ho_va_ten, ngay_sinh, dia_chi, chuc_vu, sdt, luong, TaiKhoan_id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getDate());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPosition());
            ps.setString(5, user.getPhone());
            ps.setFloat(6, user.getSalary());
            ps.setInt(7, user.getAccountId());
            if (ps.executeUpdate() > 0) {
                rs = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean deleteUser(int id) {
        boolean rs = false;
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "Update NhanVien set is_active = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, id);
            rs = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean updateUser(User user) {
        boolean rs = false;
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "UPDATE NhanVien  SET ho_va_ten = ?, ngay_sinh = ?, dia_chi = ?, chuc_vu = ?, sdt = ?, luong = ?, TaiKhoan_id = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getDate());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPosition());
            ps.setString(5, user.getPhone());
            ps.setDouble(6, user.getSalary());
            ps.setInt(7, user.getAccountId());
            ps.setInt(8, user.getId());
            rs = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public User getIdUser(int id) {
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE id = ? and is_active = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 1); // is_active = 1
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("ho_va_ten"));
                user.setDate(rs.getString("ngay_sinh"));
                user.setAddress(rs.getString("dia_chi"));
                user.setPosition(rs.getString("chuc_vu"));
                user.setPhone(rs.getString("sdt"));
                user.setSalary(rs.getFloat("luong"));
                user.setDateCreate(rs.getTimestamp("createdAt"));
                user.setAccountId(rs.getInt("TaiKhoan_id"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> SearchAllUserByName(String name) {
        List<User> users = new ArrayList<User>();
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE  ho_va_ten LIKE '%" + name + "%' and is_active = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("ho_va_ten"));
                user.setDate(rs.getString("ngay_sinh"));
                user.setAddress(rs.getString("dia_chi"));
                user.setPosition(rs.getString("chuc_vu"));
                user.setPhone(rs.getString("sdt"));
                user.setSalary(rs.getFloat("luong"));
                user.setDateCreate(rs.getTimestamp("createdAt"));
                user.setAccountId(rs.getInt("TaiKhoan_id"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> SearchAllUserById(int id) {
        List<User> users = new ArrayList<User>();
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE id = ? and is_active = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("ho_va_ten"));
                user.setDate(rs.getString("ngay_sinh"));
                user.setAddress(rs.getString("dia_chi"));
                user.setPosition(rs.getString("chuc_vu"));
                user.setPhone(rs.getString("sdt"));
                user.setSalary(rs.getFloat("luong"));
                user.setDateCreate(rs.getTimestamp("createdAt"));
                user.setAccountId(rs.getInt("TaiKhoan_id"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> SearchAllUserByPhone(String phone) {
        List<User> users = new ArrayList<User>();
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE sdt LIKE '%" + phone + "%' and is_active = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1); // is_active = 1
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("ho_va_ten"));
                user.setDate(rs.getString("ngay_sinh"));
                user.setAddress(rs.getString("dia_chi"));
                user.setPosition(rs.getString("chuc_vu"));
                user.setPhone(rs.getString("sdt"));
                user.setSalary(rs.getFloat("luong"));
                user.setDateCreate(rs.getTimestamp("createdAt"));
                user.setAccountId(rs.getInt("TaiKhoan_id"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getNameUser(String name) {
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE ho_va_ten = ? and is_active = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, 1); // is_active = 1
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("ho_va_ten"));
                user.setDate(rs.getString("ngay_sinh"));
                user.setAddress(rs.getString("dia_chi"));
                user.setPosition(rs.getString("chuc_vu"));
                user.setPhone(rs.getString("sdt"));
                user.setSalary(rs.getFloat("luong"));
                user.setDateCreate(rs.getTimestamp("createdAt"));
                user.setAccountId(rs.getInt("TaiKhoan_id"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
