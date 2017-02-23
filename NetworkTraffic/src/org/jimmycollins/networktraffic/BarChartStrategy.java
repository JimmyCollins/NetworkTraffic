
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;

public class BarChartStrategy implements ChartStrategy {
    
    @Override
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle)
    {
        // TODO: Create a bar chart using the data
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Test");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        BarChart barChart = new BarChart(xAxis, yAxis);
        
        return barChart;
    }
    
}
