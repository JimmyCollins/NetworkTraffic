
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;

public class PieChartStrategy implements ChartStrategy {
    
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