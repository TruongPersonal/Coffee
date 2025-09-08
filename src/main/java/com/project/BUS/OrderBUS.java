package com.project.BUS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.project.DAO.OrderDAO;
import com.project.DAO.OrderDetailDAO;
import com.project.DTO.OrderDTO;
import com.project.DTO.OrderDetailDTO;

public class OrderBUS {
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;

    public OrderBUS() {
        this.orderDAO = new OrderDAO();
        this.orderDetailDAO = new OrderDetailDAO();
    }

    public ArrayList<OrderDTO> getAll() {
        ArrayList<OrderDTO> orderDTOs = null;
        try {
            orderDTOs = orderDAO.getAll();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotal(String orderStatus, int pmMethod_id, Timestamp startDate,
            Timestamp endDate, Float startTotal, Float endTotal) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = null;
        try {
            orderDTOs = orderDAO.getAllWithTotal(orderStatus, pmMethod_id, startDate, endDate, startTotal, endTotal);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotalByAccountID(int accountID) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = null;
        try {
            orderDTOs = orderDAO.getAllWithTotalByAccountID(accountID);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotalByOrderID(int orderID) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = null;
        try {
            orderDTOs = orderDAO.getAllWithTotalByOrderID(orderID);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return orderDTOs;
    }

    
    public LinkedHashMap<OrderDTO, Float> getAllWithTotalByEmp_Name(String empName) {
        LinkedHashMap<OrderDTO, Float> orderDTOs = null;
        try {
            orderDTOs = orderDAO.getAllWithTotalByEmp_Name(empName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return orderDTOs;
    }

    public LinkedHashMap<OrderDTO, Float> getAllWithTotal() {
        LinkedHashMap<OrderDTO, Float> orderDTOs = null;
        try {
            orderDTOs = orderDAO.getAllWithTotal();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return orderDTOs;
    }

    public boolean addOrder(OrderDTO order, ArrayList<OrderDetailDTO> orderDetails) {
        try {
            return orderDAO.addOrder(order, orderDetails);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public ArrayList<OrderDetailDTO> getOrderDetails(int orderID) {
        try {
            return orderDetailDAO.getByOrderID(orderID);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public boolean updateStatus(int orderID, String status) {
        boolean rs = false;
        try {
            rs = orderDAO.updateStatus(orderID, status);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rs;
    }
}
