package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.project.DTO.CartDTO;
import com.project.DTO.ProductDTO;

public class CartDAO {

    public static Boolean insertCart(CartDTO cart) {
        int result = 0;
        try {
            Connection conn = mysqlConnect.getConnection();
            CartDTO check_cart = getCartByProductId(cart.getProductId(), cart.getAccountId());

            if (check_cart == null) {
                String sql = "insert into GioHang (SanPham_id,so_luong, TaiKhoan_id)  values (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, cart.getProductId());
                pst.setInt(2, cart.getQuantity());
                pst.setInt(3, cart.getAccountId());
                result = pst.executeUpdate();

                if (result > 0) {
                    return true;
                }

            } else {
                String sql = "update GioHang set so_luong = ?  where id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                int new_quantity = check_cart.getQuantity() + 1;
                pst.setInt(1, new_quantity);
                pst.setInt(2, check_cart.getId());
                result = pst.executeUpdate();

                if (result > 0) {
                    return true;
                }
            }
            mysqlConnect.closeConnection(conn);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public static Boolean updateQuantityInCart(int cart_id, int quantity) {
        int result = 0;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "update GioHang set so_luong = ? where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, quantity);
            pst.setInt(2, cart_id);
            result = pst.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static Boolean removeItemInCart(int cart_id) {
        int result = 0;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "delete from GioHang where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, cart_id);
            result = pst.executeUpdate();

            return result > 0 ? true : false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static ArrayList<CartDTO> getCartByAccountId(int user_id) {
        ArrayList<CartDTO> list_cart = new ArrayList<>();

        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "GioHang.id, " +
                    "SanPham_id, " +
                    "GioHang.so_luong AS so_luong, " +
                    "TaiKhoan_id, " +
                    "GioHang.createdAt, " +
                    "SanPham.id AS product_id, " +
                    "ten_SP, " +
                    "url_anh, " +
                    "gia, " +
                    "SanPham.is_active, " +
                    "SanPham.so_luong AS product_quantity, " +
                    "ten_loai, " +
                    "LoaiSanPham.id AS category_id, " +
                    "SanPham.createdAt AS product_createdAt, " +
                    "SanPham.updatedAt AS product_updatedAt " +
                    "FROM " +
                    "GioHang " +
                    "JOIN " +
                    "SanPham ON GioHang.SanPham_id = SanPham.id " +
                    "JOIN " +
                    "TaiKhoan ON GioHang.TaiKhoan_id = TaiKhoan.id " +
                    "JOIN " +
                    "LoaiSanPham ON SanPham.LoaiSanPham_id = LoaiSanPham.id " +
                    "WHERE " +
                    "TaiKhoan_id = ? " +
                    "GROUP BY " +
                    "ten_SP;";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("so_luong");
                int account_id = rs.getInt("TaiKhoan_id");

                int idProduct = rs.getInt("SanPham_id");
                String tensp = rs.getString("ten_SP");
                String anh = rs.getString("url_anh");
                float gia = rs.getFloat("gia");
                int soluong = rs.getInt("product_quantity");
                int hoatdong = rs.getInt("is_active");
                Timestamp ngaylap = rs.getTimestamp("product_createdAt");
                Timestamp ngaycapnhat = rs.getTimestamp("product_updatedAt");
                int loaisp = rs.getInt("category_id");
                String tenloai = rs.getString("ten_loai");

                LocalDateTime lcdt = ngaylap.toLocalDateTime();
                LocalDateTime ncn = ngaycapnhat != null ? ngaycapnhat.toLocalDateTime() : null;

                ProductDTO product = new ProductDTO(
                        idProduct, tensp, anh, gia, hoatdong, soluong, loaisp, tenloai, lcdt, ncn);

                list_cart.add(new CartDTO(id, quantity, product, account_id));
            }
            return list_cart;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public static CartDTO getCartByProductId(int product_id, int id_account) {
        CartDTO cart = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "select * from GioHang where SanPham_id = ? and TaiKhoan_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, product_id);
            pst.setInt(2, id_account);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int account_id = rs.getInt("TaiKhoan_id");
                int productId = rs.getInt("SanPham_id");
                int quantity = rs.getInt("so_luong");
                cart = new CartDTO(id, quantity, productId, account_id);
            }
            return cart;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<CartDTO> list = getCartByAccountId(1);
        for (CartDTO cartDTO : list) {
            System.out.println(cartDTO.getProductDTO().getId());
        }
    }
}
