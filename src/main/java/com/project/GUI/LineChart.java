package com.project.GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.project.BUS.StatisticalBUS;
import com.project.DTO.StatisticalDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LineChart {
    public LineChart() {

    }

    public static ChartPanel createLineChart(HashMap<String, ArrayList<StatisticalDTO>> dataChart, String title) {
        // Tạo dataset

        DefaultCategoryDataset dataset = createDataset(dataChart);

        // Tạo biểu đồ từ dataset
        JFreeChart chart = ChartFactory.createLineChart(
                title, // Tiêu đề biểu đồ
                "Thời gian", // Nhãn trục x
                "Giá trị", // Nhãn trục y
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL, // Hướng biểu đồ,
                true,
                true,
                true);

        // Thiết lập màu và kiểu đường cho các dòng
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();

        // Đặt tên cho đường nét liền của Series 1
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // Đường nét liền cho Series 1
        renderer.setSeriesPaint(0, Color.GREEN); // Màu cho Series 1

        // Đặt tên cho đường nét liền của Series 2
        renderer.setSeriesStroke(1, new BasicStroke(2.0f)); // Đường nét liền cho Series 2
        renderer.setSeriesPaint(1, Color.BLUE); // Màu cho Series 2
        // Đặt tên cho đường nét liền của Series 2
        renderer.setSeriesStroke(2, new BasicStroke(2.0f)); // Đường nét liền cho Series 2
        renderer.setSeriesPaint(2, Color.RED); // Màu cho Series 2

        plot.setRenderer(renderer);

        // Tạo Panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Thêm Panel vào JFrame
        // setContentPane(chartPanel);
        return chartPanel;
    }

    private static DefaultCategoryDataset createDataset(HashMap<String, ArrayList<StatisticalDTO>> chartData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // HashMap<String, ArrayList<StatisticalDTO>> chartData =
        // StatisticalBUS.getDataChartOverView();
        if (chartData == null) {
            return null;
        }

        ArrayList<StatisticalDTO> revenueData = chartData.get("revenue");
        ArrayList<StatisticalDTO> expenseData = chartData.get("expense");
        ArrayList<StatisticalDTO> profitData = chartData.get("profit");

        for (StatisticalDTO profit : profitData) {
            dataset.addValue(profit.getValues(), "Lợi nhuận",
                    profit.getDate() != null ? profit.getDate() : profit.getMonthYear());

        }
        for (StatisticalDTO revenue : revenueData) {
            dataset.addValue(revenue.getValues(), "Doanh thu",
                    revenue.getDate() != null ? revenue.getDate() : revenue.getMonthYear());

        }
        for (StatisticalDTO expense : expenseData) {
            dataset.addValue(expense.getValues(), "Chi phí",
                    expense.getDate() != null ? expense.getDate() : expense.getMonthYear());

        }
        // dataset.addValue(2.0, "Lợi nhuận", "Category 2");
        // dataset.addValue(5.0, "Lợi nhuận", "Category 3");
        // dataset.addValue(2.0, "Lợi nhuận", "Category 4");
        // dataset.addValue(0.0, "Lợi nhuận", "Category 5");

        // dataset.addValue(1.0, "Doanh thu", "Category 1");
        // dataset.addValue(4.0, "Doanh thu", "Category 2");
        // dataset.addValue(3.0, "Doanh thu", "Category 3");
        // dataset.addValue(5.0, "Doanh thu", "Category 4");
        // dataset.addValue(2.0, "Doanh thu", "Category 5");

        // dataset.addValue(2.0, "Chi phí", "Category 1");
        // dataset.addValue(7.0, "Chi phí", "Category 2");
        // dataset.addValue(3.0, "Chi phí", "Category 3");
        // dataset.addValue(4.0, "Chi phí", "Category 4");
        // dataset.addValue(9.0, "Chi phí", "Category 5");

        return dataset;
    }

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // frame.add(new LineChart().createLineChart());
    // frame.setVisible(true);
    // }
}
