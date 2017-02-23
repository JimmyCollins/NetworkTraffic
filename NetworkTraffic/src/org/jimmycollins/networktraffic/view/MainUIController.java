
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.ChartContext;
import org.jimmycollins.networktraffic.PieChartStrategy;
import org.jimmycollins.networktraffic.model.TrafficFileStats;
import org.jimmycollins.networktraffic.util.TrafficFileParser;
import org.jimmycollins.networktraffic.util.Utility;

public class MainUIController implements Initializable 
{   
    @FXML
    private TabPane tabPane;
    
    private final ChartContext chartContext = new ChartContext();
    
    @FXML
    private void handleSelectTrafficFile(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("binetflow files (*.binetflow)", "*.binetflow");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        
        try
        {
            TrafficFileStats stats = TrafficFileParser.ParseFile(file);
            
            Map<String,Integer> topSourceIPAddresses = Utility.GetTopElements(stats.GetSourceHosts(), 5);
            Map<String,Integer> topDestinationIPAddresses = Utility.GetTopElements(stats.GetDestinationHosts(), 5);
            
            chartContext.setChartStrategy(new PieChartStrategy());
            
            Tab sourceIPAddresses = chartContext.createChartTab(topSourceIPAddresses, "Top Source IP Addresses");
            sourceIPAddresses.setText("Source IP");
            tabPane.getTabs().add(sourceIPAddresses);
            
            Tab destinationIPAddresses = chartContext.createChartTab(topDestinationIPAddresses, "Top Destination IP Addresses");
            destinationIPAddresses.setText("Destination IP");
            tabPane.getTabs().add(destinationIPAddresses);
            
            // TODO: Some Bar Charts
            
            
            
            
        }
        catch(Exception ex)
        {
            // TODO: Log Exception
            Utility.Alert(AlertType.ERROR, "Error", ex.getMessage() + "\n" + ex.getStackTrace());
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
