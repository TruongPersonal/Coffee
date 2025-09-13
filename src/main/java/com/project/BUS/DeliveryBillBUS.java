package com.project.BUS;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.project.Common.Common;
import com.project.DAO.DeliveryBillDAO;
import com.project.DAO.EnterCouponDAO;
import com.project.DTO.DeliveryBillDTO;
import com.project.DTO.DetailDeliveryBillDTO;
import com.project.DTO.EnterCouponDTO;

public class DeliveryBillBUS {
    public static Boolean createDeliveryBillBUS(DeliveryBillDTO deliveryBill,
            ArrayList<DetailDeliveryBillDTO> list_detail) {
        return DeliveryBillDAO.createDeliveryBill(deliveryBill, list_detail);

    }

    // getAllDeliveryBillSort

    public static ArrayList<DeliveryBillDTO> getAllDeliveryBill() {
        return DeliveryBillDAO.getDeliveryBill();
    }

    public static ArrayList<DetailDeliveryBillDTO> getDetailDeliveryBillBUS(int id) {
        return DeliveryBillDAO.getDetailDeliveryBill(id);
    }

    // searchDeliveryBillByNameBUS
    public static ArrayList<DeliveryBillDTO> searchDeliveryBillByNameBUS(String search_deliveryBill) {
        return DeliveryBillDAO.SearchDeliveryBillByName(search_deliveryBill);
    }

    public static ArrayList<DeliveryBillDTO> searchDeliveryBillAdvanced(String startTime, String endTime,
            int startTotalReceipt, int endTotalReceipt) {

        ArrayList<DeliveryBillDTO> allDeliveryBill = null;
        if ((startTime != null || endTime != null) && (startTotalReceipt == -1 || endTotalReceipt == -1)) {

            allDeliveryBill = DeliveryBillDAO.searchDeliveryBillByDay(startTime, endTime);

        }
        if ((startTime == null || endTime == null) && (startTotalReceipt != -1 || endTotalReceipt != -1)) {

            allDeliveryBill = DeliveryBillDAO.searchDeliveryBillByAmount(startTotalReceipt, endTotalReceipt);

        }

        if ((startTime != null || endTime != null) && (startTotalReceipt != -1 || endTotalReceipt != -1)) {

            allDeliveryBill = DeliveryBillDAO.searchDeliveryBillByAmountAndDate(startTime, endTime, startTotalReceipt,
                    endTotalReceipt);
        }

        if (allDeliveryBill == null) {
            return null;
        }
        return allDeliveryBill;
    }

    // removeDeliveryBillBUS
    public static Boolean removeDeliveryBillBUS(int id, String createdAt) {

        // // Chuyển đổi createdAt từ String sang kiểu Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date createdDate;
        try {
            createdDate = dateFormat.parse(createdAt);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Lấy thời gian hiện tại
        Date currentDate = new Date();

        // Tính khoảng thời gian giữa thời điểm tạo và thời điểm hiện tại
        long timeDifference = currentDate.getTime() - createdDate.getTime();

        // Chuyển đổi thời gian từ mili giây sang giờ
        long hoursDifference = timeDifference / (1000 * 60 * 60);

        if (hoursDifference < 24) {
            ArrayList<DetailDeliveryBillDTO> list_detail = DeliveryBillDAO.getDetailDeliveryBill(id);
            for (DetailDeliveryBillDTO detailDeliveryBillDTO : list_detail) {
                
            }
            return DeliveryBillDAO.removeDeliveryBill(id, list_detail);

        } else {
            JOptionPane.showMessageDialog(null, "Không được phép xóa sau 24 giờ kể từ thời điểm tạo.");
            return false;
        }

    }

    public static void main(String[] args) {
        removeDeliveryBillBUS(26, "28/04/2024 22:32:19");
    }
}
