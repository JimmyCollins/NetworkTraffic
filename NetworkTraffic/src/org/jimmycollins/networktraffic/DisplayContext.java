
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.Chart;

/**
 * Display context used by Strategy Pattern implementation
 */
public class DisplayContext 
{    
    private DisplayStrategy chartStrategy;
    
    /**
     * Set the chart strategy to be used by the UI
     * @param chartStrategy The chart strategy to be used
     */
    public void setChartStrategy(DisplayStrategy chartStrategy) 
    {
        this.chartStrategy = chartStrategy;
    }
    
    
    /**
     * Create a tab for use on the UI with the given information
     * @param chartData The data to be used in the chart
     * @param chartTitle The title to be used on the chart
     * @return A new tab for use on the UI with the given information
     */
    public Chart createChart(Map<String,Integer> chartData, String chartTitle) 
    {
        return chartStrategy.ChartData(chartData, chartTitle);
    }
}
