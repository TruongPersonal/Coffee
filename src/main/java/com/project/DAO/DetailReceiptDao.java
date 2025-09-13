
package com.project.DAO;
import com.project.DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DetailReceiptDao {
    public void addDetailReceipt(DetailReceipt detailReceipt) throws SQLException
    {
        Connection connection = mysqlConnect.getConnection();
        String sql = "INSERT INTO ChiTietPhieuNhap(PhieuNhap_id, NguyenLieu_id, so_luong, don_vi, don_gia, updatedAt) VALUES (?, ?, ?, ?, ?, NOW())";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, detailReceipt.getIdReceipt());
        ps.setInt(2, detailReceipt.getIdIngredient());
        ps.setFloat(3, detailReceipt.getQuantity());
        ps.setString(4, detailReceipt.getUnit());
        ps.setFloat(5, detailReceipt.getPrice());
        ps.executeUpdate();
    }
}
