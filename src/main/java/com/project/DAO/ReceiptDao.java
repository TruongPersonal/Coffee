
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

public class ReceiptDao {
    public void addReceipt(Receipt receipt) 
    {
        Connection connection = mysqlConnect.getConnection();
        String sql = "INSERT INTO PhieuNhap(ten_phieu_nhap, TaiKhoan_id, NhaCungCap_id, updatedAt) VALUES(?, ?, ?, NOW())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, receipt.getName());
            ps.setInt(2, receipt.getIdAccount());
            ps.setInt(3, receipt.getIdSupplier());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReceiptDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Receipt> getAllReceipt()
    {
        List<Receipt> receipts = new ArrayList<Receipt>();
        Connection connection = mysqlConnect.getConnection();
        try 
        {
            String sql = "SELECT * FROM PhieuNhap";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt("id"));
                receipt.setName(rs.getString("ten_phieu_nhap"));
                receipt.setIdAccount(rs.getInt("TaiKhoan_id"));
                receipt.setIdSupplier(rs.getInt("NhaCungCap_id"));
                receipt.setCreateDate(rs.getTimestamp("createdAt"));
                receipt.setUpdateDate(rs.getTimestamp("updatedAt"));
                receipts.add(receipt);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return receipts;
    }
}
