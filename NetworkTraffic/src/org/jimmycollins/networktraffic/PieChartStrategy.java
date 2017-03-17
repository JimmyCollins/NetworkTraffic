
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;

/**
 * Defines functionality necessary for creating Pie Charts on the UI
 */
public class PieChartStrategy implements DisplayStrategy {
    
    /**
     * Create a Pie Chart from the given data
     * @param chartData The data from which to create the chart
     * @param chartTitle The title to use for the chart
     * @return A Pie Chart from the given data
     */
    @Override
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle)
    {
        PieChart chart = new PieChart();
        
        chartData.entrySet().stream().map((entry) -> new PieChart.Data(entry.getKey(), entry.getValue())).forEachOrdered((data) -> 
            {
                chart.getData().add(data);
            });
        
        chart.setTitle(chartTitle);
        
        return chart;
    }
}