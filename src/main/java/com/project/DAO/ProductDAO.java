package com.project.DAO;

// <<<<<<< HEAD
import java.sql.*;

import java.time.LocalDateTime;

// =======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
// >>>>>>> luan
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.time.format.DateTimeFormatter;

import com.project.DTO.ProductDTO;

public class ProductDAO {

    public static ArrayList<ProductDTO> getAll() {
        ArrayList<ProductDTO> products = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from SanPham where is_active = 1";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            products = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String product_name = rs.getString("ten_SP");
                String url_image = rs.getString("url_anh");
                float price = rs.getFloat("gia");
                int is_active = rs.getInt("is_active");
                int quantity = rs.getInt("so_luong");
                LocalDateTime createAt = rs.getTimestamp("createdAt").toLocalDateTime();
                int category_id = rs.getInt("LoaiSanPham_id");
                products.add(
                        new ProductDTO(id, product_name, url_image, price, is_active, quantity, category_id, createAt));

            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return products;
    }

    public ProductDTO getProductByID(int product_id) {
        ProductDTO productDTO = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from SanPham Where id = ? and is_active = 1";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String product_name = rs.getString("ten_SP");
                float price = rs.getFloat("gia");
                productDTO = new ProductDTO(id, product_name, price);
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return productDTO;
    }

    public ProductDTO getProductByID_IgnoreActiveState(int product_id) {
        ProductDTO productDTO = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from SanPham Where id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String product_name = rs.getString("ten_SP");
                float price = rs.getFloat("gia");
                productDTO = new ProductDTO(id, product_name, price);
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return productDTO;
    }

    public static ArrayList<ProductDTO> getProductByCategory(int category) {
        ArrayList<ProductDTO> listProduct = null;
        try {
            // tao ket noi tu database len arraylist
            Connection con = mysqlConnect.getConnection();

            // viet sql
            String sql = "SELECT " +
                    "SanPham.id," +
                    "ten_SP," +
                    "url_anh," +
                    "gia," +
                    "SanPham.is_active," +
                    "so_luong," +
                    "ten_loai," +
                    "LoaiSanPham.id as loaisp_id, " +
                    "SanPham.createdAt," +
                    "SanPham.updatedAt " +
                    "FROM SanPham " +
                    "JOIN LoaiSanPham ON SanPham.LoaiSanPham_id = LoaiSanPham.id " +
                    "WHERE SanPham.is_active = 1 and SanPham.LoaiSanPham_id = ? " +
                    "ORDER BY  id  ASC ";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, category);

            // nhan du lieu tu database tra ve
            ResultSet rls = pst.executeQuery();
            listProduct = new ArrayList<>();
            while (rls.next()) {
                int id = rls.getInt("id");
                String tensp = rls.getString("ten_SP");
                String anh = rls.getString("url_anh");
                float gia = rls.getFloat("gia");
                int hoatdong = rls.getInt("is_active");
                int soluong = rls.getInt("so_luong");
                Timestamp ngaylap = rls.getTimestamp("createdAt");
                LocalDateTime lcdt = ngaylap.toLocalDateTime();
                String tenloai = rls.getString("ten_loai");
                int loaisp = rls.getInt("loaisp_id");
                Timestamp ngaycapnhat = rls.getTimestamp("updatedAt");
                LocalDateTime ncn = ngaycapnhat != null ? ngaycapnhat.toLocalDateTime() : null;
                listProduct.add(new ProductDTO(id, tensp, anh, gia, hoatdong, soluong, loaisp, tenloai, lcdt, ncn));

            }
            mysqlConnect.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return listProduct;
    }

    public static ArrayList<ProductDTO> allProduct() {
        ArrayList<ProductDTO> listProduct = new ArrayList<>();
        try {
            // tao ket noi tu database len arraylist
            Connection con = mysqlConnect.getConnection();

            // viet sql
            String sql = "SELECT " +
                    "SanPham.id," +
                    "ten_SP," +
                    "url_anh," +
                    "gia," +
                    "SanPham.is_active," +
                    "so_luong," +
                    "ten_loai," +
                    "LoaiSanPham.id as loaisp_id, " +
                    "SanPham.createdAt," +
                    "SanPham.updatedAt " +
                    "FROM SanPham " +
                    "JOIN LoaiSanPham ON SanPham.LoaiSanPham_id = LoaiSanPham.id " +
                    "WHERE SanPham.is_active = 1 " +
                    "ORDER BY  id  ASC ";

            PreparedStatement pst = con.prepareStatement(sql);

            // nhan du lieu tu database tra ve
            ResultSet rls = pst.executeQuery();
            while (rls.next()) {
                int id = rls.getInt("id");
                String tensp = rls.getString("ten_SP");
                String anh = rls.getString("url_anh");
                float gia = rls.getFloat("gia");
                int hoatdong = rls.getInt("is_active");
                int soluong = rls.getInt("so_luong");
                Timestamp ngaylap = rls.getTimestamp("createdAt");
                LocalDateTime lcdt = ngaylap.toLocalDateTime();
                String tenloai = rls.getString("ten_loai");
                int loaisp = rls.getInt("loaisp_id");
                Timestamp ngaycapnhat = rls.getTimestamp("updatedAt");
                LocalDateTime ncn = ngaycapnhat != null ? ngaycapnhat.toLocalDateTime() : null;
                listProduct.add(new ProductDTO(id, tensp, anh, gia, hoatdong, soluong, loaisp, tenloai, lcdt, ncn));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProduct;
    }

    public static boolean productExists(String nameProduct) {
        try {
            // tao ket noi tu database len arraylist
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT COUNT(*) FROM SanPham WHERE ten_SP = ? and is_active = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nameProduct);
            pst.setInt(2, 1);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;

            } else {
                return false;
            }

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    // tim kiem
    public static ArrayList<ProductDTO> searchProductDAO(String keyword) {
        ArrayList<ProductDTO> listProduct = new ArrayList<>();
        try {
            // tao ket noi tu database len arraylist
            Connection con = mysqlConnect.getConnection();

            // viet sql
            String sql = "SELECT " +
                    "SanPham.id," +
                    "ten_SP," +
                    "url_anh," +
                    "gia," +
                    "SanPham.is_active," +
                    "so_luong," +
                    "ten_loai," +
                    "LoaiSanPham.id as loaisp_id, " +
                    "SanPham.createdAt," +
                    "SanPham.updatedAt " +
                    "FROM SanPham " +
                    "JOIN LoaiSanPham ON SanPham.LoaiSanPham_id = LoaiSanPham.id " +
                    "WHERE SanPham.is_active = 1 and ten_SP like ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, keyword + "%");
            // nhan du lieu tu database tra ve
            ResultSet rls = pst.executeQuery();
            while (rls.next()) {
                int id = rls.getInt("id");
                String tensp = rls.getString("ten_SP");
                String anh = rls.getString("url_anh");
                float gia = rls.getFloat("gia");
                int hoatdong = rls.getInt("is_active");
                int soluong = rls.getInt("so_luong");
                Timestamp ngaylap = rls.getTimestamp("createdAt");
                LocalDateTime lcdt = ngaylap.toLocalDateTime();
                String tenloai = rls.getString("ten_loai");
                int loaisp = rls.getInt("loaisp_id");
                Timestamp ngaycapnhat = rls.getTimestamp("updatedAt");
                LocalDateTime ncn = ngaycapnhat != null ? ngaycapnhat.toLocalDateTime() : null;
                listProduct.add(new ProductDTO(id, tensp, anh, gia, hoatdong, soluong, loaisp, tenloai, lcdt, ncn));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProduct;
    }

    // them vao database
    public static boolean addProductDAO(ProductDTO product) {
        try {
            Connection con = mysqlConnect.getConnection();
            String insertSql = "insert into SanPham(ten_SP, url_anh, gia, so_luong, LoaiSanPham_id, createdAt) values(?,?,?,?,?, NOW())";
            PreparedStatement pst = con.prepareStatement(insertSql);
            pst.setString(1, product.getProduct_name());
            pst.setString(2, product.getUrl_image());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setInt(5, product.getCategory_id());
            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Xử lý ngoại lệ khi cố gắng chèn một bản ghi trùng lặp
            JOptionPane.showMessageDialog(null, "Tên sản phẩm đã tồn tại trong cơ sở dữ liệu.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // chinh sua san pham
    public static boolean updateProductDAO(ProductDTO product) {
        try {
            Connection con = mysqlConnect.getConnection();
            String updateSql = "update SanPham set ten_SP =?, url_anh =?, gia =?, so_luong =?, LoaiSanPham_id =?, updatedAt = NOW() where id =? ";
            PreparedStatement pst = con.prepareStatement(updateSql);
            pst.setString(1, product.getProduct_name());
            pst.setString(2, product.getUrl_image());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setInt(5, product.getCategory_id());
            pst.setInt(6, product.getId());
            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    // xoa san pham
    public static boolean deleteProductDAO(int id) {
        try {
            Connection con = mysqlConnect.getConnection();
            String deleteSql = "update SanPham set is_active = ?, updatedAt = NOW() where id =? ";
            PreparedStatement pst = con.prepareStatement(deleteSql);
            pst.setInt(1, 0);
            pst.setInt(2, id);
            if (pst.executeUpdate() > 0) {
                System.out.println("Product Deleted");
                return true;
            } else {
                System.out.println("Error");
                return false;

            }
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

    }

    public static boolean increaseProductQuantity(int product_id, int quantity) {
        boolean rs = false;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Update SanPham set so_luong = so_luong + ? Where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(2, product_id);
            pst.setInt(1, quantity);
            int result = pst.executeUpdate();
            if (result > 0) {
                rs = true;
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public static boolean decreaseProductQuantity(int product_id, int quantity) {
        boolean rs = false;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Update SanPham set so_luong = so_luong - ? Where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(2, product_id);
            pst.setInt(1, quantity);
            int result = pst.executeUpdate();
            if (result > 0) {
                rs = true;
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    // get product by id
    public static ProductDTO getProductById(int id) {
        ProductDTO product = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from SanPham where id =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("ten_SP");
                String imageUrl = rs.getString("url_anh");
                float price = rs.getFloat("gia");
                int isActive = rs.getInt("is_active");
                int quantity = rs.getInt("so_luong");
                int categoryId = rs.getInt("LoaiSanPham_id");

                product = new ProductDTO(productId, productName, imageUrl, price, quantity, categoryId);

            }
            mysqlConnect.closeConnection(conn);
            return product;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {
        ArrayList<ProductDTO> data = getAll();

        for (ProductDTO productDTO : data) {
            System.out.println(productDTO.getProduct_name());
        }
    }

}
