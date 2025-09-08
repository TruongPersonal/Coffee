package com.project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.collections4.iterators.ArrayListIterator;

import com.project.DTO.EmployeeDTO;

public class EmployeeDAO {

    public static ArrayList<EmployeeDTO> get_all_employee() {
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        try {
            Connection conn = mysqlConnect.getConnection();
            String sql = "Select * from NhanVien";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet result_query = pst.executeQuery();
            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name = result_query.getString("ho_va_ten");
                int account_id = result_query.getInt("TaiKhoan_id");

                employees.add(new EmployeeDTO(id, name, account_id));
            }
        } catch (Exception e) {
            return null;
        }
        return employees;
    }

    public EmployeeDTO getEmpByAcountID(int accountID) {
        EmployeeDTO emp = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            // <<<<<<< HEAD
            // String sql = "Select NV.* from NhanVien NV, TaiKhoan TK WHERE TK.id = ? AND
            // NV.TaiKhoan_id = TK.id";
            // =======
            String sql = "Select * from NhanVien WHERE TaiKhoan_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, accountID);
            ResultSet result_query = pst.executeQuery();

            while (result_query.next()) {
                int id = result_query.getInt("id");
                String name = result_query.getString("ho_va_ten");
                int account_id = result_query.getInt("TaiKhoan_id");

                emp = new EmployeeDTO(id, name, account_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }

    public ArrayList<EmployeeDTO> getEmpList_ByAcountID(ArrayList<Integer> accountID_List) {
        ArrayList<EmployeeDTO> empList = null;
        try {
            Connection conn = mysqlConnect.getConnection();
            empList = new ArrayList<>();

            for (int accID : accountID_List) {
                String sql = "Select * from NhanVien WHERE TaiKhoan_id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, accID);
                ResultSet result_query = pst.executeQuery();

                if (result_query.next()) {
                    int id = result_query.getInt("id");
                    String name = result_query.getString("ho_va_ten");
                    int account_id = result_query.getInt("TaiKhoan_id");

                    empList.add(new EmployeeDTO(id, name, account_id));
                } else {
                    empList.add(null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return empList;
    }
}
