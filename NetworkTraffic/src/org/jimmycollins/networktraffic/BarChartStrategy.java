
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Defines functionality necessary for creating Bar Charts on the UI
 */
public class BarChartStrategy implements DisplayStrategy {
    
    /**
     * Create a Bar Chart from the given data
     * @param chartData The data from which to create the chart
     * @param chartTitle The title to use for the chart
     * @return A Bar Chart from the given data
     */
    @Override
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle)
    {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart barChart = new BarChart(xAxis, yAxis);
        
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(chartTitle);
        
        chartData.entrySet().stream().map((entry) -> new XYChart.Data(entry.getKey(), entry.getValue())).forEachOrdered((data) -> 
            {
                dataSeries1.getData().add(data);
            });

        barChart.getData().add(dataSeries1);
        
        return barChart;
    }
}
