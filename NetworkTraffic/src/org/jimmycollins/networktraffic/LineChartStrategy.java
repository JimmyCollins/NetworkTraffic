
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Defines functionality necessary for creating Line Charts on the UI
 */
public class LineChartStrategy implements DisplayStrategy {
    
    /**
     * Create a Line Chart from the given data
     * @param chartData The data from which to create the chart
     * @param chartTitle The title to use for the chart
     * @return A Line Chart from the given data
     */
    @Override
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle)
    {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        xAxis.setLabel(chartTitle);
        
        LineChart<String,Number> lineChart = new LineChart<>(xAxis, yAxis);
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Count");
        
        chartData.entrySet().stream().map((entry) -> new XYChart.Data(entry.getKey(), entry.getValue())).forEachOrdered((data) -> 
            {
                series.getData().add(data);
            });
        
        lineChart.getData().add(series);     
        lineChart.setTitle(chartTitle);
            
        return lineChart;
    } 
}
