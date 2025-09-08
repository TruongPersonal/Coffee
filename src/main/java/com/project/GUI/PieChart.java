package com.project.GUI;

import org.jfree.chart.*;

import java.awt.Color;
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

class PieChart {

    public static ChartPanel createChart(String title, ArrayList<?> data) {
        // Create the chart
        JFreeChart chart = ChartFactory.createPieChart(
                title, // chart title
                createDataset(
                        data), // data
                true, // include legend
                true,
                false);

        // Customize the appearance
        chart.setBackgroundPaint(Color.WHITE);

        // Create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        // chartPanel.setPreferredSize(new Dimension(400, 300));

        return chartPanel;

    }

    public static DefaultPieDataset createDataset(ArrayList<?> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Object object : data) {
            if (object instanceof StatisticalDTO) {
                StatisticalDTO list_data = (StatisticalDTO) object;
                dataset.setValue(list_data.getLabel(), list_data.getValues());
            }
        }
        dataset.setValue("Oranges", 30);
        dataset.setValue("Bananas", 20);

        return dataset;
    }
}