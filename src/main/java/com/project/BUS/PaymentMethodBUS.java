package com.project.BUS;

import java.util.ArrayList;

import com.project.DAO.PaymentMethodDAO;
import com.project.DTO.PaymentMethodDTO;

public class PaymentMethodBUS {
    private PaymentMethodDAO pmDAO;

    public PaymentMethodBUS() {
        this.pmDAO = new PaymentMethodDAO();
    }

    public ArrayList<PaymentMethodDTO> getAll() {
        ArrayList<PaymentMethodDTO> rs = null;
        try {
            rs = pmDAO.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public PaymentMethodDTO getByID(int id) {
        try {
            return pmDAO.getByID(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
