package com.project.BUS;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.DAO.StatisticalDAO;
import com.project.DTO.StatisticalDTO;
import com.project.DTO.StatisticalProductDTO;

public class StatisticalBUS {

    public static HashMap<String, ArrayList<StatisticalDTO>> getDataChartOverView() {
        ArrayList<StatisticalDTO> data = StatisticalDAO.getProfitRevenueExpenseFor30Day();
        ArrayList<StatisticalDTO> data_revenue = new ArrayList<>();
        ArrayList<StatisticalDTO> data_expense = new ArrayList<>();
        ArrayList<StatisticalDTO> data_profit = new ArrayList<>();

        for (StatisticalDTO statisticalDTO : data) {
            data_revenue.add(new StatisticalDTO(statisticalDTO.getRevenue(), statisticalDTO.getDate()));
            data_expense.add(new StatisticalDTO(statisticalDTO.getExpense(), statisticalDTO.getDate()));
            data_profit.add(new StatisticalDTO(statisticalDTO.getProfit(), statisticalDTO.getDate()));
        }

        HashMap<String, ArrayList<StatisticalDTO>> result = new HashMap<>();
        result.put("revenue", data_revenue);
        result.put("expense", data_expense);
        result.put("profit", data_profit);

        return result;
    }

    public static HashMap<String, ArrayList<StatisticalDTO>> getDataChartOverViewChooseTime(String startDate,
            String endDate) {
        ArrayList<StatisticalDTO> data = StatisticalDAO.getProfitRevenueExpense(startDate, endDate);
        ArrayList<StatisticalDTO> data_revenue = new ArrayList<>();
        ArrayList<StatisticalDTO> data_expense = new ArrayList<>();
        ArrayList<StatisticalDTO> data_profit = new ArrayList<>();

        for (StatisticalDTO statisticalDTO : data) {
            data_revenue.add(new StatisticalDTO(statisticalDTO.getRevenue(), statisticalDTO.getDate()));
            data_expense.add(new StatisticalDTO(statisticalDTO.getExpense(), statisticalDTO.getDate()));
            data_profit.add(new StatisticalDTO(statisticalDTO.getProfit(), statisticalDTO.getDate()));
        }

        HashMap<String, ArrayList<StatisticalDTO>> result = new HashMap<>();
        result.put("revenue", data_revenue);
        result.put("expense", data_expense);
        result.put("profit", data_profit);

        return result;
    }

    public static HashMap<String, ArrayList<StatisticalDTO>> getDataChartOverViewByMonth() {
        ArrayList<StatisticalDTO> data = StatisticalDAO.getProfitRevenueExpenseByMonth();
        ArrayList<StatisticalDTO> data_revenue = new ArrayList<>();
        ArrayList<StatisticalDTO> data_expense = new ArrayList<>();
        ArrayList<StatisticalDTO> data_profit = new ArrayList<>();

        if (data == null || data.size() == 0) {
            return null;
        }

        for (StatisticalDTO statisticalDTO : data) {
            data_revenue.add(new StatisticalDTO(statisticalDTO.getRevenue(), statisticalDTO.getMonthYear()));
            data_expense.add(new StatisticalDTO(statisticalDTO.getExpense(), statisticalDTO.getMonthYear()));
            data_profit.add(new StatisticalDTO(statisticalDTO.getProfit(), statisticalDTO.getMonthYear()));
        }

        HashMap<String, ArrayList<StatisticalDTO>> result = new HashMap<>();
        result.put("revenue", data_revenue);
        result.put("expense", data_expense);
        result.put("profit", data_profit);

        return result;
    }

    public static HashMap<String, Integer> getDateForItemOverView() {
        float totalProfit = StatisticalDAO.getTotalProfit();
        int totalProduct = StatisticalDAO.getTotalProduct();
        int totalOrder = StatisticalDAO.getTotalOrder();
        int totalSupplier = StatisticalDAO.getTotalSupplier();
        int totalEmployee = StatisticalDAO.getTotalEmployee();
        int totalIngredient = StatisticalDAO.getTotalIngredient();

        HashMap<String, Integer> result = new HashMap<>();
        result.put("totalProfit", (int) totalProfit);
        result.put("totalProduct", totalProduct);
        result.put("totalOrder", totalOrder);
        result.put("totalSupplier", totalSupplier);
        result.put("totalEmployee", totalEmployee);
        result.put("totalIngredient", totalIngredient);

        return result;
    }

    public static HashMap<String, StatisticalProductDTO> getDataForItemProduct() {
        StatisticalProductDTO dataBestSeller = StatisticalDAO.getBestSellerProduct();
        StatisticalProductDTO dataBestRevenue = StatisticalDAO.getBestRevenueProduct();

        HashMap<String, StatisticalProductDTO> result = new HashMap<>();
        result.put("bestSeller", dataBestSeller);
        result.put("bestRevenue", dataBestRevenue);
        // result.put("totalOrder", totalOrder);
        // result.put("totalSupplier", totalSupplier);
        // result.put("totalEmployee", totalEmployee);
        // result.put("totalIngredient", totalIngredient);

        return result;
    }

    public static ArrayList<StatisticalProductDTO> getDataBarChart1Product() {
        ArrayList<StatisticalProductDTO> dataTotalSold = StatisticalDAO.getTotalSoldByProduct();

        return dataTotalSold;
    }

    public static ArrayList<StatisticalProductDTO> getDataBarChart1ProductChooseDate(String startDate, String endDate) {
        ArrayList<StatisticalProductDTO> dataTotalSold = StatisticalDAO.getTotalSoldByProductChooseDate(startDate,
                endDate);

        return dataTotalSold;
    }

    public static ArrayList<StatisticalProductDTO> getDataBarChart2Product() {
        ArrayList<StatisticalProductDTO> dataTotalSold = StatisticalDAO.getQuantityByProduct();

        return dataTotalSold;
    }

    public static ArrayList<StatisticalDTO> getDataChartWarehouses() {
        return StatisticalDAO.expenseImportWarehousesBy30day();
    }
    public static ArrayList<StatisticalDTO> getDataChartWarehousesChooseDate(String startTime, String endTime) {
        return StatisticalDAO.expenseImportWarehousesChooseDay(startTime,endTime);
    }
    
    public static ArrayList<StatisticalDTO> getDataChartStatisticalIngredient() {
        return StatisticalDAO.StatisticalIngredient();
    }


    public static void main(String[] args) {
       ArrayList<StatisticalDTO> list = getDataChartWarehouses();
    for (StatisticalDTO statisticalDTO : list) {
        System.out.println(statisticalDTO.getDate());
        System.out.println(statisticalDTO.getValues());
    }
    }
}
