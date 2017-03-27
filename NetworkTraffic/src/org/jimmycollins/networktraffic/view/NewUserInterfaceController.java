
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.BarChartStrategy;
import org.jimmycollins.networktraffic.DisplayContext;
import org.jimmycollins.networktraffic.DisplayStrategy;
import org.jimmycollins.networktraffic.LineChartStrategy;
import org.jimmycollins.networktraffic.ParsingException;
import org.jimmycollins.networktraffic.PieChartStrategy;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.model.Observer;
import org.jimmycollins.networktraffic.model.ParsableFile;
import org.jimmycollins.networktraffic.util.BinetFile;
import org.jimmycollins.networktraffic.util.LogUtil;
import org.jimmycollins.networktraffic.util.Utility;


public class NewUserInterfaceController implements Initializable {
    
    @FXML
    private Label parsedFlowsLabel;
    
    @FXML
    private Label parsingErrorsLabel;
    
    @FXML
    private Label totalPacketsLabel;
    
    @FXML
    private Pane topSourcePortsPane;
    
    @FXML
    private Pane topDestinationPortsPane;
    
    @FXML
    private Pane topSourceIPsPane;
    
    @FXML
    private Pane topDestinationIPsPane;
    
    
    private final DisplayContext chartContext = new DisplayContext();
    
    private FlowFileStats stats = new FlowFileStats();
    
    private File file;
    
    
    private final Locale locale = new Locale("en", "US");
    private final ResourceBundle resources = ResourceBundle.getBundle("ResourcesBundle", locale);
    
    
    
    @FXML
    private void handleParseNewFile(ActionEvent event)
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
                LogUtil.Log(Alert.AlertType.WARNING, resources.getString("alertheader"), resources.getString("nofileselectederror"));
                return;
            }
            
            /*long fileSize = file.length();
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
                LogUtil.Log(Alert.AlertType.INFORMATION, resources.getString("alertheader"), resources.getString("bigfilewarning"));
            }*/
                 
            // Parse the traffic file     
            // Example of Dynamic Binding here
            ParsableFile pfile = new BinetFile(file);
            stats = pfile.ParseFile(stats);
            
            
            
            //showBarChartsButton.setDisable(false);
            //showPieChartsButton.setDisable(false);
            //showLineChartsButton.setDisable(false);
        }
        catch(ParsingException ex)
        {
            LogUtil.Log(LogUtil.LogLevel.SEVERE, "Parsing Error. Exception was: " + ex.toString());
            LogUtil.Log(Alert.AlertType.ERROR, resources.getString("error"), resources.getString("parsingerror"));
        }
    }
    
    
    
    
    @FXML
    private void handleDrawGeneralCharts(ActionEvent event)
    {
        // Draw bar charts by default (user can change from the UI)
        drawGeneralCharts(new PieChartStrategy());
    }
      
    @FXML
    private void handleLoadSession(ActionEvent event)
    {
        // TODO
    }
    
    @FXML
    private void handleSaveSession(ActionEvent event)
    {
        // TODO
    }
    
    
    @FXML
    private void handleDrawBarCharts(ActionEvent event)
    {
        drawGeneralCharts(new BarChartStrategy());
    }
    
    
    @FXML
    private void handleDrawLineCharts(ActionEvent event)
    {
        drawGeneralCharts(new LineChartStrategy());
    }
    
    
    private void drawGeneralCharts(DisplayStrategy strategy)
    {
        chartContext.setChartStrategy(strategy);
        
        topSourcePortsPane.getChildren().clear();
        topDestinationPortsPane.getChildren().clear();
        topSourceIPsPane.getChildren().clear();
        topDestinationIPsPane.getChildren().clear();
        
        // Top Source Ports
        Chart sourcePorts = chartContext.createChart(stats.GetTopSourcePorts(), resources.getString("topsourceports"));
        topSourcePortsPane.getChildren().add(sourcePorts);
        
        // Top Destination Ports
        Chart destinationPorts = chartContext.createChart(stats.GetTopDestinationPorts(), resources.getString("topdestinationports"));
        topDestinationPortsPane.getChildren().add(destinationPorts);
        
        // Top Source IP Addresses
        Chart topSourceIPAddresses = chartContext.createChart(stats.GetTopSourceIPAddresses(), resources.getString("topsourceipaddresses"));
        topSourceIPsPane.getChildren().add(topSourceIPAddresses);
        
        // Top Destination IP Addresses
        Chart topDestinationIPAddresses = chartContext.createChart(stats.GetTopDestinationIPAddresses(), resources.getString("topdestinationipaddresses"));
        topDestinationIPsPane.getChildren().add(topDestinationIPAddresses);
           
        // Top Protocols
        /*Tab protocols = chartContext.createChartTab(stats.GetTopProtocols(), resources.getString("topprotocols"));
        protocols.setText(resources.getString("protocols"));
        tabPane.getTabs().add(protocols);*/
        
    }
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        stats.attach(new NewUserInterfaceController.ParsingObserver(stats));   
    }    
    
    
    // Nested Class used to update the statistics panel on the UI as parsing progresses
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
                    }
                  });     
        }
    
    }
    
}
