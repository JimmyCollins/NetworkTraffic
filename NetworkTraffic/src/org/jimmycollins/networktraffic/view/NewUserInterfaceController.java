
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
import org.jimmycollins.networktraffic.util.Database;
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
    
    @FXML
    private Button saveCurrentAnalysisBtn;
    
    @FXML
    private Button loadExistingAnalysisButton;
    
    
    Map<String,Integer> sourcePortData = new HashMap<>();
    Map<String,Integer> destinationPortData = new HashMap<>();
    Map<String,Integer> sourceIpData = new HashMap<>();
    Map<String,Integer> destinationIpData = new HashMap<>();
    
    
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
        saveCurrentAnalysisBtn.setDisable(false);
    }
      
    @FXML
    private void handleLoadSession(ActionEvent event) throws IOException
    {
        // Get the list of saved sessions from the database
        
        // Get a connection to the database
        Connection db = Database.getInstance().getConnection();
        
        try
        {
            String query = "select Date from savedanalyses";       
            Statement st = db.createStatement(); 
            ResultSet rs = st.executeQuery(query);
            
            List<String> savedAnalyses = new ArrayList<>();
            
            while (rs.next())
            {
                String date = rs.getString("Date");
                savedAnalyses.add(date);
            }
            
            ChoiceDialog<String> dialog = new ChoiceDialog<>("", savedAnalyses);
            dialog.setTitle("Network Traffic Analyzer");
            dialog.setHeaderText("Choose the existing analysis you want to load");
            dialog.setContentText("Choice:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (!result.isPresent())
            {
                System.out.println("User cancelled.");
                return;
            }
            
            System.out.println("User choice: " + result.get());
            
            
            // TODO - Load this data and plug it into the charts
            
            
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Error", ex.toString());
        }
    }
    
    @FXML
    private void handleSaveSession(ActionEvent event)
    {
        // Confirm the user wishes to save the data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Network Traffic Analyzer");
        alert.setContentText("Do you wish to save the current analysis to the database?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL)
        {
            return;
        }      
        
        // Get a connection to the database
        Connection db = Database.getInstance().getConnection();
        
        try
        {
            // First we need to save a row in the savedanalysis table
            String timestamp = Utility.GenerateTimestamp();
            String savedAnalysesQuery = "insert into savedanalyses (AnalysisId, Date)"
                              + " values (NULL, '" + timestamp + "')";
            
            int analysisId = 0; // The auto-incremented primary key in the savedanalyses table - referenced when other metrics are saved
            
            PreparedStatement savedAnalysesStatement = db.prepareStatement(savedAnalysesQuery, Statement.RETURN_GENERATED_KEYS);      
            savedAnalysesStatement.executeUpdate();
            
            ResultSet rs = savedAnalysesStatement.getGeneratedKeys();
            
            if (rs.next()) 
            {
               analysisId = rs.getInt(1);   
            }
            
            
            // TODO - Some sort of DB helper class to reduce duplicated code here?
            
            
            // Save the Source Port Data            
            for (Map.Entry<String, Integer> entry : sourcePortData.entrySet())
            {
                String port = entry.getKey();
                Integer count = entry.getValue();
                
                String sourcePortQuery = "insert into topsourceports (Id, AnalysisId, Port, Count)"
                              + " values (NULL, " + analysisId + "," + port + "," + count + ")";
                
                PreparedStatement sourcePortStatement = db.prepareStatement(sourcePortQuery);      
                sourcePortStatement.executeUpdate();
            }
            
            // Save the Desination Port Data
            for (Map.Entry<String, Integer> entry : destinationPortData.entrySet())
            {
                String port = entry.getKey();
                Integer count = entry.getValue();
                
                String destinationPortQuery = "insert into topdestinationports (Id, AnalysisId, Port, Count)"
                              + " values (NULL, " + analysisId + "," + port + "," + count + ")";
                
                PreparedStatement destinationPortStatement = db.prepareStatement(destinationPortQuery);      
                destinationPortStatement.executeUpdate();
            }
            
            // Save the Source IP Data
            for (Map.Entry<String, Integer> entry : sourceIpData.entrySet())
            {
                String ip = entry.getKey();
                Integer count = entry.getValue();
                
                String sourceIpQuery = "insert into topsourceips (Id, AnalysisId, IP, Count)"
                              + " values (NULL, " + analysisId + ",'" + ip + "'," + count + ")";
                
                PreparedStatement sourceIpStatement = db.prepareStatement(sourceIpQuery);      
                sourceIpStatement.executeUpdate();
            }
            
            // Save the Destination IP Data
            for (Map.Entry<String, Integer> entry : destinationIpData.entrySet())
            {
                String ip = entry.getKey();
                Integer count = entry.getValue();
                
                String destinationIpQuery = "insert into topdestinationips (Id, AnalysisId, IP, Count)"
                              + " values (NULL, " + analysisId + ",'" + ip + "'," + count + ")";
                
                PreparedStatement destinationIpStatement = db.prepareStatement(destinationIpQuery);      
                destinationIpStatement.executeUpdate();
            }
            
            LogUtil.Log(Alert.AlertType.INFORMATION, "Network Traffic Analyzer", "Current Analysis data was saved to the database successfully as '" + timestamp + "'"
                    + "\n\nUse the Load Existing Analysis option to load this data again in the future.");
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Error", ex.toString());
        }
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
        sourcePortData = stats.GetTopSourcePorts();
        Chart sourcePorts = chartContext.createChart(sourcePortData, resources.getString("topsourceports"));
        topSourcePortsPane.getChildren().add(sourcePorts);
        
        // Top Destination Ports
        destinationPortData = stats.GetTopDestinationPorts();
        Chart destinationPorts = chartContext.createChart(destinationPortData, resources.getString("topdestinationports"));
        topDestinationPortsPane.getChildren().add(destinationPorts);
        
        // Top Source IP Addresses
        sourceIpData = stats.GetTopSourceIPAddresses();
        Chart topSourceIPAddresses = chartContext.createChart(sourceIpData, resources.getString("topsourceipaddresses"));
        topSourceIPsPane.getChildren().add(topSourceIPAddresses);
        
        // Top Destination IP Addresses
        destinationIpData = stats.GetTopDestinationIPAddresses();
        Chart topDestinationIPAddresses = chartContext.createChart(destinationIpData, resources.getString("topdestinationipaddresses"));
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
