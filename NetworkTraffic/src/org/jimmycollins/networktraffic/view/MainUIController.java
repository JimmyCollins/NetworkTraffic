
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.BarChartStrategy;
import org.jimmycollins.networktraffic.DisplayContext;
import org.jimmycollins.networktraffic.DisplayStrategy;
import org.jimmycollins.networktraffic.PieChartStrategy;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.util.FlowFileParser;
import org.jimmycollins.networktraffic.util.Utility;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import org.jimmycollins.networktraffic.model.Observer;

public class MainUIController implements Initializable
{   
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Tab generalTab;
    
    @FXML
    //private ListView<String> logs;
    private TableView tableView;
    
    @FXML
    private Button showBarChartsButton;
    
    
    
    private final DisplayContext chartContext = new DisplayContext();
       
    private FlowFileStats stats = new FlowFileStats();
    
    private File file;
    
    @FXML
    private ChoiceBox chartChoice;
    
    @FXML
    private void handleSelectTrafficFile(ActionEvent event)
    {    
        try
        {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("binetflow files (*.binetflow)", "*.binetflow");
            fileChooser.getExtensionFilters().add(extFilter);
            file = fileChooser.showOpenDialog(new Stage());
        
            // Parse the traffic file
            //stats = FlowFileParser.ParseFile(file);  
                        
            // Show Pie Charts by default
            //chartContext.setChartStrategy(new PieChartStrategy());
            
            // Draw the charts based on the data in the passed file
            
            drawCharts(file, new PieChartStrategy());
            showBarChartsButton.setDisable(false);
        }
        catch(Exception ex)
        {
            // TODO: Log Exception
            Utility.Alert(AlertType.ERROR, "Error", ex.getMessage() + "\n" + ex.toString());
        }

    }
    
    
    @FXML
    private void handleSelectChangeGraphs(ActionEvent event)
    {
        // Allows user to show Bar Charts instead of Pie Charts
        //tabPane.getTabs().clear();     
        //chartContext.setChartStrategy(new BarChartStrategy());
        drawCharts(file, new BarChartStrategy());
    }
    
    // TODO: Allow a change back to Bar Charts
    // TODO: Add Line graphs
    
    private void drawCharts(File file, DisplayStrategy strategy)  // Should be in Chart Manager class?
    {    
        //tabPane.getTabs().clear();
        
        // Parse the traffic file
        //stats = FlowFileParser.ParseFile(file);  
        
        FlowFileParser parser = new FlowFileParser(file);
        stats = parser.ParseFile(stats);

        chartContext.setChartStrategy(strategy);
        
        Map<String,Integer> topSourcePorts = stats.GetTop5SourcePorts();
        Map<String,Integer> topDestinationPorts = stats.GetTop5DestinationPorts();
        Map<String,Integer> topSourceIPAddresses = stats.GetTop5SourceIPAddresses();
        Map<String,Integer> topDestinationIPAddresses = stats.GetTop5DestinationIPAddresses();
        
        // Top Source Ports
        Tab sourcePorts = chartContext.createChartTab(topSourcePorts, "Top Source Ports");
        sourcePorts.setText("Source Ports");
        tabPane.getTabs().add(sourcePorts);
        
        // Top Destination Ports
        Tab destinationPorts = chartContext.createChartTab(topDestinationPorts, "Top Destination Ports");
        destinationPorts.setText("Destination Ports");
        tabPane.getTabs().add(destinationPorts);
        
        // Top Source IP Addresses
        Tab sourceIPAddresses = chartContext.createChartTab(topSourceIPAddresses, "Top Source IP Addresses");
        sourceIPAddresses.setText("Source IP");
        tabPane.getTabs().add(sourceIPAddresses);
        
        // Top Destination IP Addresses
        Tab destinationIPAddresses = chartContext.createChartTab(topDestinationIPAddresses, "Top Destination IP Addresses");
        destinationIPAddresses.setText("Destination IP");
        tabPane.getTabs().add(destinationIPAddresses);
        
        // Top Protocols
        Tab protocols = chartContext.createChartTab(stats.GetTop5Protocols(), "Top Protocols");
        protocols.setText("Protocols");
        tabPane.getTabs().add(protocols);
        
        // TODO: More Charts
        
        // TODO: Add General stats tab that always gets added?
        // Total packets
        // Packets parsed from file
        // Total Bytes
        // Averge Bytes?
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //chartChoice.getItems().add("Pie Charts");
        //chartChoice.getItems().add("Bar Charts");

        stats.attach(new ParsingObserver(stats));
        
    }    
    
    // Inner Class that handles updating the 'General' table
    private class ParsingObserver extends Observer {
        
        ParsingObserver(FlowFileStats stats) {
            this.stats = stats ;
        }

        @Override
        public void update() {
            System.out.println("In update");
            tableView.getItems().setAll(stats.GetSourcePorts());
        }
       
    }
    
}
