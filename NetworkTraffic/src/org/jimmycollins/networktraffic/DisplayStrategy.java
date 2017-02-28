
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.chart.Chart;

public interface DisplayStrategy
{    
    public Chart ChartData(Map<String,Integer> chartData, String chartTitle);
}