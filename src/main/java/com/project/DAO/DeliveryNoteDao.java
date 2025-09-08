
package com.project.DAO;
import com.project.DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryNoteDao {
    public void addDeliveryNote(DeliveryNote deliveryNote) 
    {
        Connection connection = mysqlConnect.getConnection();
        String sql = "INSERT INTO PhieuXuat(ten_phieu_xuat, TaiKhoan_id, updatedAt) VALUES(?, ?, NOW())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, deliveryNote.getName());
            ps.setInt(2, deliveryNote.getIdAccount());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReceiptDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<DeliveryNote> getAllDeliveryNote()
    {
        List<DeliveryNote> deliveryNotes = new ArrayList<DeliveryNote>();
        Connection connection = mysqlConnect.getConnection();
        try 
        {
            String sql = "SELECT * FROM PhieuXuat";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                DeliveryNote deliveryNote = new DeliveryNote();
                deliveryNote.setId(rs.getInt("id"));
                deliveryNote.setName(rs.getString("ten_phieu_xuat"));
                deliveryNote.setIdAccount(rs.getInt("TaiKhoan_id"));
                deliveryNote.setCreateDate(rs.getTimestamp("createdAt"));
                deliveryNote.setUpdateDate(rs.getTimestamp("updatedAt"));
                deliveryNotes.add(deliveryNote);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return deliveryNotes;
    }
}
