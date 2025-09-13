package com.project.GUI;

import org.jfree.chart.*;

import java.util.ArrayList;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.project.DTO.StatisticalDTO;
import com.project.DTO.StatisticalProductDTO;

class BarChart {

    public static ChartPanel createChart(String TitleChart, ArrayList<?> data) {
        JFreeChart barChart = ChartFactory.createBarChart(
                TitleChart,
                "Năm", "VNĐ",
                createDataset(data), PlotOrientation.VERTICAL, false, false, false);
        // Lấy Renderer của biểu đồ để thiết lập nhãn giá trị
        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        renderer.setBaseItemLabelsVisible(true); // Cho phép hiển thị nhãn giá trị
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); // Sử dụng định dạng mặc
                                                                                      // định

        // Tạo một vị trí mới để hiển thị nhãn
        ItemLabelPosition position = new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12,
                TextAnchor.TOP_CENTER,
                TextAnchor.TOP_CENTER,
                Math.PI * 2);

        // Thiết lập vị trí cho nhãn giá trị
        renderer.setBasePositiveItemLabelPosition(position);

        ChartPanel chartPanel = new ChartPanel(barChart);
        return chartPanel;
    }

    public static CategoryDataset createDataset(ArrayList<?> data) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object object : data) {
            if (object instanceof StatisticalProductDTO) {
                StatisticalProductDTO product = (StatisticalProductDTO) object;
                dataset.addValue(product.getDataStatistical() == 0.0
                        ? product.getQuantity()
                        : product.getDataStatistical(), "VNĐ", product.getName());
            } else if (object instanceof StatisticalDTO) {
                StatisticalDTO statistic = (StatisticalDTO) object;
                dataset.addValue(statistic.getValues(), "VNĐ",
                        statistic.getDate());

            }
        }
        return dataset;
    }

}