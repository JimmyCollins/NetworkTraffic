
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.Chart;

public interface ChartStrategy
{    
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle);
}