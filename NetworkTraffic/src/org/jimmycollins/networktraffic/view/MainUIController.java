
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.BarChartStrategy;
import org.jimmycollins.networktraffic.DisplayContext;
import org.jimmycollins.networktraffic.DisplayStrategy;
import org.jimmycollins.networktraffic.LineChartStrategy;
import org.jimmycollins.networktraffic.PieChartStrategy;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.model.Observer;
import org.jimmycollins.networktraffic.util.BinetFile;
import org.jimmycollins.networktraffic.util.Logger;
import org.jimmycollins.networktraffic.util.Utility;

public class MainUIController implements Initializable
{   
    @FXML
    private TabPane tabPane;
    
    @FXML
    private Label parsedFlowsLabel;
    
    @FXML
    private Label parsingErrorsLabel;
    
    @FXML
    private Label fileSizeLabel;
    
    @FXML
    private Button showBarChartsButton;
    
    @FXML
    private Button showPieChartsButton;
    
    @FXML
    private Button showLineChartsButton;
    
    @FXML
    private Label totalPacketsLabel;
    
    private final DisplayContext chartContext = new DisplayContext();
       
    private FlowFileStats stats = new FlowFileStats();
    
    private File file;
    
    
    @FXML
    private void handleSelectTrafficFile(ActionEvent event)
    {    
        try
        {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("binetflow files (*.binetflow)", "*.binetflow");
            fileChooser.getExtensionFilters().add(extFilter);
            file = fileChooser.showOpenDialog(new Stage());
            
            // Ensure the user has selected a file
            if(file == null)
            {
                Logger.Log(AlertType.WARNING, "Network Traffic Analyzer", "You have not selected a file.");
                return;
            }
            
            long fileSize = file.length();
            long fileSizeAsMb = Utility.BytesToMb(fileSize);
            
            if(fileSizeAsMb < 1)
            {
                fileSizeLabel.setText("< 1MB");
            }
            else
            {
                fileSizeLabel.setText("" + fileSizeAsMb + "MB");
            }
            
            // Alert the user that parsing may take a while for large files         
            if(fileSize >= 100000000)
            {
                Logger.Log(AlertType.INFORMATION, "Network Traffic Analyzer", "This file is larger than "
                        + "100MB, and may take some time to parse. Parsing can be monitored "
                        + "on the panel on the left.");
            }
                 
            // Parse the traffic file       
            BinetFile parser = new BinetFile(file);
            stats = parser.ParseFile(stats);
            
            // TODO: Decide if we draw the charts as we go (if refresh works) or make the user draw them after parsing
            
            showBarChartsButton.setDisable(false);
            showPieChartsButton.setDisable(false);
            showLineChartsButton.setDisable(false);
        }
        catch(Exception ex)
        {
            Logger.Log(AlertType.ERROR, "Error", ex.getMessage() + "\n" + ex.toString());
        }
    }
    
    
    @FXML
    private void handleDrawBarCharts(ActionEvent event)
    {
        int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
        drawCharts(new BarChartStrategy());
        tabPane.getSelectionModel().select(currentIndex);
    }
    
    
    @FXML
    private void handleDrawPieCharts(ActionEvent event)
    {
        int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
        drawCharts(new PieChartStrategy());
        tabPane.getSelectionModel().select(currentIndex);
    }
    
    
    @FXML
    private void handleDrawLineCharts(ActionEvent event)
    {
        int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
        drawCharts(new LineChartStrategy());
        tabPane.getSelectionModel().select(currentIndex);
    }
    
    
    private void drawCharts(DisplayStrategy strategy)
    {    
        tabPane.getTabs().clear();
        
        // TODO: Draw general tab here to avoid complications with removing it??
        /*Tab tableTab = new Tab();
        table = new TableView();
        TableColumn sourceIP = new TableColumn("Source IP");
        TableColumn destinationIP = new TableColumn("Destination IP");
        
        table.getColumns().addAll(sourceIP, destinationIP);
        tableTab.setText("General");
        tableTab.setContent(table);
        tabPane.getTabs().add(tableTab);*/
        
        chartContext.setChartStrategy(strategy);
        
        // Top Source Ports
        Tab sourcePorts = chartContext.createChartTab(stats.GetTopSourcePorts(), "Top Source Ports");
        sourcePorts.setText("Source Ports");
        tabPane.getTabs().add(sourcePorts);
        
        // Top Destination Ports
        Tab destinationPorts = chartContext.createChartTab(stats.GetTopDestinationPorts(), "Top Destination Ports");
        destinationPorts.setText("Destination Ports");
        tabPane.getTabs().add(destinationPorts);
        
        // Top Source IP Addresses
        Tab sourceIPAddresses = chartContext.createChartTab(stats.GetTopSourceIPAddresses(), "Top Source IP Addresses");
        sourceIPAddresses.setText("Source IP");
        tabPane.getTabs().add(sourceIPAddresses);
        
        // Top Destination IP Addresses
        Tab destinationIPAddresses = chartContext.createChartTab(stats.GetTopDestinationIPAddresses(), "Top Destination IP Addresses");
        destinationIPAddresses.setText("Destination IP");
        tabPane.getTabs().add(destinationIPAddresses);
        
        // Top Protocols
        Tab protocols = chartContext.createChartTab(stats.GetTopProtocols(), "Top Protocols");
        protocols.setText("Protocols");
        tabPane.getTabs().add(protocols);
        
        // TODO: More Charts
        
        // TODO: Add General stats tab that always gets added?
        // Total packets
        // Packets parsed from file
        // Total Bytes
        // Average Bytes?
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        stats.attach(new ParsingObserver(stats));    
    }    
    
    // Inner class that handles updating the stats panel on the UI
    private class ParsingObserver extends Observer {
        
        ParsingObserver(FlowFileStats stats) {
            this.stats = stats ;
        }

        @Override
        public void update() {
            
            System.out.println("In update");
                
            // Anonymous Inner Class
            Platform.runLater(new Runnable() {
                    @Override public void run() {
                      parsedFlowsLabel.setText(""+stats.GetParsedFlows());
                      parsingErrorsLabel.setText(""+stats.GetUnparsableFlows());
                      totalPacketsLabel.setText(""+stats.GetTotalPacketCount());
                      
                      // TODO
                    }
                  });     
    }
    
}
}
