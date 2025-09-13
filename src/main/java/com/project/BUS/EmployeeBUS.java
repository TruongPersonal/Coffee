package com.project.BUS;


import java.util.ArrayList;

import com.project.DAO.EmployeeDAO;
import com.project.DTO.EmployeeDTO;

public class EmployeeBUS {
    public EmployeeDAO employeeDAO;

    public EmployeeBUS() {
        this.employeeDAO = new EmployeeDAO();
    }

    public EmployeeDTO getEmpByAccountID(int accountID) {
        EmployeeDTO emp = null;
        try {
            emp = employeeDAO.getEmpByAcountID(accountID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }


    public ArrayList<EmployeeDTO> getEmpList_ByAccountID(ArrayList<Integer> accountID_List) {
        ArrayList<EmployeeDTO> emp = null;
        try {
            emp = employeeDAO.getEmpList_ByAcountID(accountID_List);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}
