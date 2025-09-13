
package com.project.DAO;
import com.project.DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DetailDeliveryNoteDao {
    public void addDetailDeliveryNote(DetailDeliveryNote detailDeliveryNote) throws SQLException
    {
        Connection connection = mysqlConnect.getConnection();
        String sql = "INSERT INTO ChiTietPhieuXuat(PhieuXuat_id, NguyenLieu_id, so_luong, don_vi, updatedAt) VALUES (?, ?, ?, ?, NOW())";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, detailDeliveryNote.getIdDeliveryNote());
        ps.setInt(2, detailDeliveryNote.getIdIngredient());
        ps.setFloat(3, detailDeliveryNote.getQuantity());
        ps.setString(4, detailDeliveryNote.getUnit());
        ps.executeUpdate();
    }
}
