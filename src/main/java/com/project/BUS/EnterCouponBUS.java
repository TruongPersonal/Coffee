package com.project.BUS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.project.DAO.EnterCouponDAO;
import com.project.DAO.ProductDAO;
import com.project.DAO.WareHouseDAO;
import com.project.DTO.DetailEnterCouponDTO;
import com.project.DTO.DetailRecipeDTO;
import com.project.DTO.EnterCouponDTO;
import com.project.DTO.WareHouse;

public class EnterCouponBUS {

    public static Boolean createdEnterCoupon(EnterCouponDTO enterCoupon, ArrayList<DetailEnterCouponDTO> new_detail,
            Map<Integer, Integer> quantity_productMap) {

        if (!new_detail.isEmpty()) {
            new_detail.remove(0); // Removes the element at index 0 (first element)
        }

        Boolean check = EnterCouponDAO.createEnterCoupon(enterCoupon, new_detail);

        if (check) {

            for (Map.Entry<Integer, Integer> entry : quantity_productMap.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                ProductDAO.increaseProductQuantity(productId, quantity); // Correct usage with valid key and value
            }
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "có lỗi xảy ra.");
            return false;
        }

    }

    public static ArrayList<EnterCouponDTO> getAllEnterCouponsBUS() {
        ArrayList<EnterCouponDTO> allEnterCoupons = EnterCouponDAO.getAllEnterCoupon();
        if (allEnterCoupons == null) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!");
            return null;
        }
        return allEnterCoupons;
    }

    public static ArrayList<EnterCouponDTO> searchEnterCouponsByNameBUS(String keyword) {
        ArrayList<EnterCouponDTO> allEnterCoupons = EnterCouponDAO.SearchEnterCouponByName(keyword);
        if (allEnterCoupons == null) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!");
            return null;
        }
        return allEnterCoupons;
    }

    public static ArrayList<EnterCouponDTO> searchEnterCouponsBySupplierBUS(String keyword) {
        ArrayList<EnterCouponDTO> allEnterCoupons = EnterCouponDAO.SearchEnterCouponBySupplier(keyword);
        if (allEnterCoupons == null) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!");
            return null;
        }
        return allEnterCoupons;
    }

    public static ArrayList<EnterCouponDTO> searchEnterCouponsByEmployeeBUS(String keyword) {
        ArrayList<EnterCouponDTO> allEnterCoupons = EnterCouponDAO.SearchEnterCouponByEmployee(keyword);
        if (allEnterCoupons == null) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!");
            return null;
        }
        return allEnterCoupons;
    }

    public static ArrayList<EnterCouponDTO> searchReceiptAdvanced(String startTime, String endTime,
            int startTotalReceipt, int endTotalReceipt) {

        ArrayList<EnterCouponDTO> allEnterCoupons = null;
        if ((startTime != null || endTime != null) && (startTotalReceipt == -1 || endTotalReceipt == -1)) {

            allEnterCoupons = EnterCouponDAO.searchEnterCouponByDay(startTime, endTime);

        }
        if ((startTime == null || endTime == null) && (startTotalReceipt != -1 || endTotalReceipt != -1)) {

            allEnterCoupons = EnterCouponDAO.searchEnterCouponByTotalReceipt(startTotalReceipt, endTotalReceipt);

        }

        if ((startTime != null || endTime != null) && (startTotalReceipt != -1 || endTotalReceipt != -1)) {

            allEnterCoupons = EnterCouponDAO.searchEnterCouponByTotalReceiptAndDate(startTime, endTime,
                    startTotalReceipt, endTotalReceipt);
        }

        if (allEnterCoupons == null) {
            return null;
        }
        return allEnterCoupons;
    }

    public static ArrayList<DetailEnterCouponDTO> getDetailEnterCoupon(int id) {
        return EnterCouponDAO.getDetailEnterCoupon(id);
    }

    public static Boolean removeEnterCouponBUS(int id, String createdAt) {

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
            ArrayList<DetailEnterCouponDTO> list_detail = EnterCouponDAO.getDetailEnterCoupon(id);
            return EnterCouponDAO.removeEnterCoupon(id, list_detail);

        } else {
            JOptionPane.showMessageDialog(null, "Không được phép xóa sau 24 giờ kể từ thời điểm tạo.");
            return false;
        }
    }

    public static boolean updateQuantityIngredient(int productId, int quantity) {
        try {

            ArrayList<DetailRecipeDTO> data_recipe = RecipeBUS.getRecipeByProductId(productId);
            for (DetailRecipeDTO detailRecipeDTO : data_recipe) {
                int id_ingredient = detailRecipeDTO.getId_ingredient();
                int quantity_ingredient = detailRecipeDTO.getQuantity();

                int quantity_ingredient_resume = quantity * quantity_ingredient;
                WareHouseDAO.decreaseIngredientWarehouse(id_ingredient, quantity_ingredient_resume);

            }

            return true;
        } catch (Exception e) {
            return false;// TODO: handle exception
        }
    }

    public static void main(String[] args) {
        // ArrayList<EnterCouponDTO> a = searchReceiptAdvanced("2024-04-14",
        // "2024-04-20", -1, -1);
        ArrayList<EnterCouponDTO> a = searchReceiptAdvanced("2024-04-15", "2024-04-23", 1, 123132);

        for (EnterCouponDTO enterCouponDTO : a) {
            System.out.println(enterCouponDTO.getId());
            System.out.println(enterCouponDTO.getNameEnterCoupon());
            System.out.println(enterCouponDTO.getNameEmployee());
            System.out.println(enterCouponDTO.getNameSupplier());
            System.out.println(enterCouponDTO.getTotalValues());
            System.out.println(enterCouponDTO.getCreatedAt());
        }
    }

}
