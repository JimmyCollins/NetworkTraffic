
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.Locale;
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
import org.jimmycollins.networktraffic.ParsingException;
import org.jimmycollins.networktraffic.PieChartStrategy;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.model.Observer;
import org.jimmycollins.networktraffic.model.ParsableFile;
import org.jimmycollins.networktraffic.util.BinetFile;
import org.jimmycollins.networktraffic.util.LogUtil;
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
    
    private final Locale locale = new Locale("en", "US");
    private final ResourceBundle resources = ResourceBundle.getBundle("ResourcesBundle", locale);
    
    /**
     * Handler for the add traffic file button
     * @param event The triggered action event
     */
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
                LogUtil.Log(AlertType.WARNING, resources.getString("alertheader"), resources.getString("nofileselectederror"));
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
                LogUtil.Log(AlertType.INFORMATION, resources.getString("alertheader"), resources.getString("bigfilewarning"));
            }
                 
            // Parse the traffic file     
            // Example of Dynamic Binding here
            ParsableFile pfile = new BinetFile(file);
            stats = pfile.ParseFile(stats);
            
            showBarChartsButton.setDisable(false);
            showPieChartsButton.setDisable(false);
            showLineChartsButton.setDisable(false);
        }
        catch(ParsingException ex)
        {
            LogUtil.Log(LogUtil.LogLevel.SEVERE, "Parsing Error. Exception was: " + ex.toString());
            LogUtil.Log(AlertType.ERROR, resources.getString("error"), resources.getString("parsingerror"));
        }
    }
    
    /**
     * Handler for the draw bar charts button
     * @param event The triggered action event
     */
    @FXML
    private void handleDrawBarCharts(ActionEvent event)
    {
        int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
        drawCharts(new BarChartStrategy());
        tabPane.getSelectionModel().select(currentIndex);
    }
    
    /**
     * Handler for the draw pie charts button
     * @param event The triggered action event
     */
    @FXML
    private void handleDrawPieCharts(ActionEvent event)
    {
        int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
        drawCharts(new PieChartStrategy());
        tabPane.getSelectionModel().select(currentIndex);
    }
    
    /**
     * Handler for the draw line charts button
     * @param event The triggered action event
     */
    @FXML
    private void handleDrawLineCharts(ActionEvent event)
    {
        int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
        drawCharts(new LineChartStrategy());
        tabPane.getSelectionModel().select(currentIndex);
    }
    
    /**
     * Main logic used to draw the charts n the UI
     * @param DisplayStrategy The display strategy to use (e.g. Pie Charts vs. Bar Charts etc.)
     */
    private void drawCharts(DisplayStrategy strategy)
    {    
        tabPane.getTabs().clear();
        
        chartContext.setChartStrategy(strategy);
        
        // Top Source Ports
        /*Tab sourcePorts = chartContext.createChartTab(stats.GetTopSourcePorts(), resources.getString("topsourceports"));
        sourcePorts.setText(resources.getString("sourceports"));
        tabPane.getTabs().add(sourcePorts);
        
        // Top Destination Ports
        Tab destinationPorts = chartContext.createChartTab(stats.GetTopDestinationPorts(), resources.getString("topdestinationports"));
        destinationPorts.setText(resources.getString("destinationports"));
        tabPane.getTabs().add(destinationPorts);
        
        // Top Source IP Addresses
        Tab sourceIPAddresses = chartContext.createChartTab(stats.GetTopSourceIPAddresses(), resources.getString("topsourceipaddresses"));
        sourceIPAddresses.setText(resources.getString("sourceip"));
        tabPane.getTabs().add(sourceIPAddresses);
        
        // Top Destination IP Addresses
        Tab destinationIPAddresses = chartContext.createChartTab(stats.GetTopDestinationIPAddresses(), resources.getString("topdestinationipaddresses"));
        destinationIPAddresses.setText(resources.getString("destinationip"));
        tabPane.getTabs().add(destinationIPAddresses);
        
        // Top Protocols
        Tab protocols = chartContext.createChartTab(stats.GetTopProtocols(), resources.getString("topprotocols"));
        protocols.setText(resources.getString("protocols"));
        tabPane.getTabs().add(protocols);*/
        
        // TODO: More Charts
        
        // Total Bytes
        // Average Bytes
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        stats.attach(new ParsingObserver(stats));    
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