
package org.jimmycollins.networktraffic;

import java.util.Map;
import javafx.scene.control.Tab;

public class DisplayContext 
{    
    private DisplayStrategy chartStrategy;
    
    public void setChartStrategy(DisplayStrategy chartStrategy) 
    {
        this.chartStrategy = chartStrategy;
    }
    
    public Tab createChartTab(Map<String,Integer> chartData, String chartTitle) 
    {
        Tab tab = new Tab();
        tab.setContent(chartStrategy.ChartData(chartData, chartTitle));
        return tab;
    }
}
