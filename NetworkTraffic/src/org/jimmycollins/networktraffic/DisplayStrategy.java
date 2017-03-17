
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.Chart;

/**
 * Defines a display strategy interface which defines a method used to chart data on the UI
 */
public interface DisplayStrategy
{    
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle);
}