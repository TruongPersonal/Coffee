package com.project.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.project.DTO.AccountDTO;
import com.project.DTO.EmployeeDTO;

public class AccountDAO {
    public static ArrayList<AccountDTO> getAllUser() {
        ArrayList<AccountDTO> listAccount = null;
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT tk.id, tk.email, tk.password, tk.createdAt, tk.updatedAt, q.ten_quyen " +
                    "FROM TaiKhoan tk " +
                    "JOIN Quyen q ON tk.Quyen_id = q.id " +
                    "WHERE  tk.is_active = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            listAccount = new ArrayList<AccountDTO>();
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String role = result.getString("ten_quyen");
                LocalDateTime createdAt = result.getTimestamp("createdAt").toLocalDateTime();
                Timestamp updateAt = result.getTimestamp("updatedAt");
                LocalDateTime updatedAt = updateAt != null ? updateAt.toLocalDateTime() : null;
                listAccount.add(new AccountDTO(id, email, password, role, createdAt, updatedAt));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listAccount;
    }

    public static ArrayList<AccountDTO> getAllUser(ArrayList<Integer> accountID_List) {
        ArrayList<AccountDTO> listAccount = null;
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * From TaiKhoan Where id = ?";

            listAccount = new ArrayList<AccountDTO>();
            for (int accID : accountID_List) {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, accID);
                ResultSet result = pst.executeQuery();
                if (result.next()) {
                    int id = result.getInt("id");
                    String email = result.getString("email");
                    int role_id = result.getInt("quyen_id");
                    listAccount.add(new AccountDTO(id, email, role_id));
                } else {
                    listAccount.add(null);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listAccount;
    }

    public static AccountDTO getUser(AccountDTO account) {
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE is_active = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, account.getEmail());
            ResultSet result = pst.executeQuery();
            AccountDTO accountDTO = null;
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                accountDTO = new AccountDTO(id, email, password);
            }
            return accountDTO;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    public static AccountDTO getUserByEmail(String email) {
        AccountDTO data = null;

        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT TaiKhoan.id, email, password, ten_quyen, Quyen.id as roleId FROM TaiKhoan JOIN Quyen ON TaiKhoan.Quyen_id = Quyen.id Where email= ? and is_active = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String mail = result.getString("email");
                String password = result.getString("password");
                String roleName = result.getString("ten_quyen");
                int roleId = result.getInt("roleId");
                data = new AccountDTO(id, mail, password, roleName, roleId);
            }
            con.close(); // Đóng kết nối sau khi sử dụng
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AccountDTO getUserById(int idAccount) {
        AccountDTO data = null;

        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT id, email, password from TaiKhoan Where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idAccount);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String mail = result.getString("email");
                String password = result.getString("password");
                data = new AccountDTO(id, mail, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return data;

    }

    // get all functions
    public static HashMap<String, Boolean> getAllFunction(int roleId) {
        HashMap<String, Boolean> listFunction = new HashMap<>();
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT QuyenChucNang.id, ten_cn FROM QuyenChucNang JOIN ChucNang ON QuyenChucNang.ChucNang_id = ChucNang.id WHERE Quyen_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, roleId);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                String name = result.getString("ten_cn");
                // Gán giá trị true cho quyền
                listFunction.put(name, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listFunction;
    }

    public static boolean addUser(AccountDTO account) {
        try {
            Connection con = mysqlConnect.getConnection();
            String insertQuery = "INSERT INTO TaiKhoan (email,password,Quyen_id) VALUES(?,?,?)";
            PreparedStatement pst = con.prepareStatement(insertQuery);
            pst.setString(1, account.getEmail());
            pst.setString(2, account.getPassword());
            pst.setInt(3, 3);
            int a = pst.executeUpdate();
            if (a > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Xử lý ngoại lệ khi cố gắng cập nhật một bản ghi với một email đã tồn tại
            JOptionPane.showMessageDialog(null, "Email đã tồn tại trong cơ sở dữ liệu.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
    public static boolean addUserForAdmin(AccountDTO account) {
        try {
            Connection con = mysqlConnect.getConnection();
            String insertQuery = "INSERT INTO TaiKhoan (email,password,Quyen_id) VALUES(?,?,?)";
            PreparedStatement pst = con.prepareStatement(insertQuery);
            pst.setString(1, account.getEmail());
            pst.setString(2, account.getPassword());
            pst.setInt(3, account.getRoleId());
            int a = pst.executeUpdate();
            if (a > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Xử lý ngoại lệ khi cố gắng cập nhật một bản ghi với một email đã tồn tại
            JOptionPane.showMessageDialog(null, "Email đã tồn tại trong cơ sở dữ liệu.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteUser(int id) {
        Connection con = mysqlConnect.getConnection();
        String deleteQuery = "UPDATE TaiKhoan SET is_active =? where id =?";
        if (con != null) {
            try {
                PreparedStatement pst = con.prepareStatement(deleteQuery);
                pst.setInt(1, 0);
                pst.setInt(2, id);
                int a = pst.executeUpdate();
                if (a > 0) {
                    System.out.println("Thành công");
                    return true;
                } else {
                    System.out.println("Thất bại!!");
                    return false;
                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return false;

            }

        }
        return false;

    }

    public static boolean updateUser(AccountDTO account) {
        try {
            Connection con = mysqlConnect.getConnection();
            String selectEmailQuery = "SELECT email FROM TaiKhoan WHERE id = ?";
            PreparedStatement selectEmailStmt = con.prepareStatement(selectEmailQuery);
            selectEmailStmt.setInt(1, account.getId());
            ResultSet rs = selectEmailStmt.executeQuery();

            String oldEmail = "";
            if (rs.next()) {
                oldEmail = rs.getString("email");
            }

            if (!oldEmail.equals(account.getEmail())) {
                String updateQuery = "UPDATE TaiKhoan SET email =?, password =?, Quyen_id = ?, updatedAt = NOW() WHERE id =?";
                PreparedStatement pst = con.prepareStatement(updateQuery);
                pst.setString(1, account.getEmail());
                pst.setString(2, account.getPassword());
                pst.setInt(3, account.getRoleId());
                pst.setInt(4, account.getId());
                int a = pst.executeUpdate();
                if (a > 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                String updateQuery = "UPDATE TaiKhoan SET password =?, Quyen_id = ?, updatedAt = NOW() WHERE id =?";
                PreparedStatement pst = con.prepareStatement(updateQuery);
                pst.setString(1, account.getPassword());
                pst.setInt(2, account.getRoleId());
                pst.setInt(3, account.getId());
                int a = pst.executeUpdate();
                if (a > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            // Xử lý ngoại lệ khi cố gắng cập nhật một bản ghi với một email đã tồn tại
            JOptionPane.showMessageDialog(null, "Email đã tồn tại trong cơ sở dữ liệu.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<AccountDTO> searchAccount(String search_acc) {
        ArrayList<AccountDTO> listAccount = new ArrayList<>();

        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT " +
                    "tk.id, " +
                    "tk.email, " +
                    "tk.password, " +
                    "tk.createdAt, " +
                    "tk.updatedAt, " +
                    "q.ten_quyen " +
                    "FROM " +
                    "TaiKhoan tk " +
                    "JOIN " +
                    "Quyen q ON tk.Quyen_id = q.id " +
                    "WHERE " +
                    "tk.is_active = 1 AND tk.email LIKE ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, search_acc + "%");

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String email = result_query.getString("email");
                String password = result_query.getString("password");
                String role = result_query.getString("ten_quyen");
                LocalDateTime createdAt = result_query.getTimestamp("createdAt").toLocalDateTime();
                Timestamp updatedAt = result_query.getTimestamp("updatedAt");
                LocalDateTime updatedAtFormat = updatedAt != null ? updatedAt.toLocalDateTime() : null;
                listAccount.add(new AccountDTO(id, email, password, role, createdAt, updatedAtFormat));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listAccount;
    }

    public static ArrayList<AccountDTO> getAll_unused(int role_id) {
        ArrayList<AccountDTO> accounts = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from TaiKhoan where Quyen_id = ? ANd id NOT IN (Select TaiKhoan_id from NhanVien)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, role_id);
            ResultSet rs = pst.executeQuery();

            accounts = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                int roleID = rs.getInt("Quyen_id");
                accounts.add(new AccountDTO(id, email, roleID));
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }

    // get detail account information
    public static AccountDTO getDetailAccount(int idAccount) {
        AccountDTO account = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "SELECT " +
                    "    TaiKhoan.id, " +
                    "    email, " +
                    "    password, " +
                    "    ten_quyen, " +
                    "    TaiKhoan.createdAt, " +
                    "    TaiKhoan.updatedAt, " +
                    "    ho_va_ten, " +
                    "    ngay_sinh, " +
                    "    dia_chi, " +
                    "    sdt, " +
                    "    chuc_vu, " +
                    "    luong " +
                    "FROM TaiKhoan " +
                    "left JOIN Quyen ON TaiKhoan.Quyen_id = Quyen.id " +
                    "left JOIN NhanVien ON TaiKhoan.id = NhanVien.TaiKhoan_id " +
                    "WHERE TaiKhoan.id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idAccount);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("ten_quyen");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                LocalDateTime updatedAtFormat = updatedAt != null ? updatedAt.toLocalDateTime() : null;
                String hoten = rs.getString("ho_va_ten");
                String ngaysinh = rs.getString("ngay_sinh");
                String diachi = rs.getString("dia_chi");
                String phone = rs.getString("sdt");
                String chucvu = rs.getString("chuc_vu");
                float luong = rs.getFloat("luong");
                EmployeeDTO employee = new EmployeeDTO(hoten, ngaysinh, diachi, phone, chucvu, luong);
                account = new AccountDTO(id, email, password, role, createdAt, updatedAtFormat, employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }

    // change password
    public static boolean changePassword(AccountDTO account) {
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "UPDATE TaiKhoan SET password =? WHERE id =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, account.getPassword());
            pst.setInt(2, account.getId());
            int a = pst.executeUpdate();
            if (a > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

    }

    public static AccountDTO getAccountByAccountID(int accountID) {
        try {

            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT * FROM TaiKhoan WHERE id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, accountID);

            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String email = result_query.getString("email");
                int roleID = result_query.getInt("quyen_id");
                return (new AccountDTO(id, email, roleID));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static ArrayList<AccountDTO> getAllEditUser_unused(int role_id, int myId) {
        ArrayList<AccountDTO> accounts = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "SELECT * FROM TaiKhoan WHERE TaiKhoan.is_active = 1 AND Quyen_id = ? AND (id = ? OR id NOT IN (SELECT TaiKhoan_id FROM NhanVien))";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, role_id);
            pst.setInt(2, myId);
            ResultSet rs = pst.executeQuery();

            accounts = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                int roleID = rs.getInt("Quyen_id");
                accounts.add(new AccountDTO(id, email, roleID));
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public static AccountDTO getIdAccountUser(int id) {
        try {
            Connection con = mysqlConnect.getConnection();
            String sql = "SELECT TaiKhoan.id, TaiKhoan.email FROM NhanVien INNER JOIN TaiKhoan ON NhanVien.TaiKhoan_id = TaiKhoan.id WHERE NhanVien.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static int getLastAccountId() {
        int lastId = -1;
        try (Connection conn = mysqlConnect.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM TaiKhoan")) {

            if (rs.next()) {
                lastId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public static ArrayList<AccountDTO> getAll_unused_1(int role_id) {
        ArrayList<AccountDTO> accounts = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from TaiKhoan where TaiKhoan.is_active = 1 AND Quyen_id = ? ANd id NOT IN (Select TaiKhoan_id from NhanVien)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, role_id);
            ResultSet rs = pst.executeQuery();

            accounts = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                int roleID = rs.getInt("Quyen_id");
                accounts.add(new AccountDTO(id, email, roleID));
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public static ArrayList<AccountDTO> getAll_unused_all() {
        ArrayList<AccountDTO> accounts = null;
        try {
            Connection conn = mysqlConnect.getConnection();

            String sql = "Select * from TaiKhoan where TaiKhoan.is_active = 1 AND  id NOT IN (Select TaiKhoan_id from NhanVien)";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            accounts = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                int roleID = rs.getInt("Quyen_id");
                accounts.add(new AccountDTO(id, email, roleID));
            }
            mysqlConnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }

}