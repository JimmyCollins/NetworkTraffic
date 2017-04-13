
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.BarChartStrategy;
import org.jimmycollins.networktraffic.DisplayContext;
import org.jimmycollins.networktraffic.DisplayStrategy;
import org.jimmycollins.networktraffic.LineChartStrategy;
import org.jimmycollins.networktraffic.ParsingException;
import org.jimmycollins.networktraffic.PieChartStrategy;
import org.jimmycollins.networktraffic.model.DShieldIpInfo;
import org.jimmycollins.networktraffic.model.DShieldPortInfo;
import org.jimmycollins.networktraffic.model.FlowFileStats;
import org.jimmycollins.networktraffic.model.Observer;
import org.jimmycollins.networktraffic.model.ParsableFile;
import org.jimmycollins.networktraffic.model.TopData;
import org.jimmycollins.networktraffic.util.BinetFile;
import org.jimmycollins.networktraffic.util.DShieldApiProxy;
import org.jimmycollins.networktraffic.util.Database;
import org.jimmycollins.networktraffic.util.DatabaseUtil;
import org.jimmycollins.networktraffic.util.LogUtil;
import org.jimmycollins.networktraffic.util.TopDestinationIpAddresses;
import org.jimmycollins.networktraffic.util.TopDestinationPorts;
import org.jimmycollins.networktraffic.util.TopSourceIpAddresses;
import org.jimmycollins.networktraffic.util.TopSourcePorts;
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
    private Button drawChartsBtn;
    
    @FXML
    private Button securityAnalysisButton;
    
    @FXML
    private TabPane mainTabPane;
    
    @FXML 
    private Label threatLevelLabel;
    
    
    
    /**
     * Tables on Security Analysis Dialog
     */
    
    // Source IP Table
    
    @FXML
    private TableView sourceIpSecurityInfoTable;   
    private final ObservableList<DShieldIpInfo> sourceIPSecurityInfoData = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn sourceIpColumn;
    @FXML
    private TableColumn sourceIpBlockedColumm;
    @FXML
    private TableColumn sourceIpAttacksColumn;
    @FXML
    private TableColumn sourceIpCountryColumn;
    
    // Destination IP Table
    
    @FXML
    private TableView destinationIpSecurityInfoTable;
    private final ObservableList<DShieldIpInfo> destinationIPSecurityInfoData = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn destinationIpColumn;
    @FXML
    private TableColumn destinationIpBlockedColumm;
    @FXML
    private TableColumn destinationIpAttacksColumn;
    @FXML
    private TableColumn destinationIpCountryColumn;
    
    // Source Port Table
    
    @FXML
    private TableView sourcePortSecurityInfoTable;
    private final ObservableList<DShieldPortInfo> sourcePortSecurityInfoData = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn sourcePortColumn;
    @FXML
    private TableColumn sourcePortRecordsColumn;
    @FXML
    private TableColumn sourcePortTargetsColumn;
    @FXML
    private TableColumn sourcePortSourcesColumn;
    
    // Destination Port Table
    
    @FXML
    private TableView destinationPortSecurityInfoTable;
    private final ObservableList<DShieldPortInfo> destinationPortSecurityInfoData = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn destinationPortColumn;
    @FXML
    private TableColumn destinationPortRecordsColumn;
    @FXML
    private TableColumn destinationPortTargetsColumn;
    @FXML
    private TableColumn destinationPortSourcesColumn;
    
    // --------------------------------------------------- //
    
    
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
            
            drawChartsBtn.setDisable(false);
            securityAnalysisButton.setDisable(false);
            
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
        drawChartsInitial(new PieChartStrategy());
        saveCurrentAnalysisBtn.setDisable(false);
    }
      
    @FXML
    private void handleLoadSession(ActionEvent event) throws IOException
    {
        // Get a connection to the database
        Connection db = Database.GetInstance().GetConnection();
        
        try
        {
            String query = "select Date from savedanalyses";       
            ResultSet rs = DatabaseUtil.ExecuteQuery(query);
            
            List<String> savedAnalyses = new ArrayList<>();
            
            while (rs.next())
            {
                String date = rs.getString("Date");
                savedAnalyses.add(date);
            }
            
            ChoiceDialog<String> dialog = new ChoiceDialog<>("", savedAnalyses);
            dialog.setTitle(resources.getString("alertheader"));
            dialog.setHeaderText(resources.getString("choosesaveddata"));
            dialog.setContentText(resources.getString("choice"));

            Optional<String> result = dialog.showAndWait();
            if (!result.isPresent())
            {
                return;
            }
            
            String chosenDate = result.get();
            drawChartsBtn.setDisable(true);
            securityAnalysisButton.setDisable(false);
            
            // Load data from the database
            
            // First we need to get the ID of this saved analysis - this is used to grab the data from the other tables
            String savedAnalysisQuery = "select * from savedanalyses where Date='" + chosenDate + "'";         
            ResultSet savedAnalysisResult = DatabaseUtil.ExecuteQuery(savedAnalysisQuery);
            
            String savedAnalysisId = "";
            while (savedAnalysisResult.next())
            {
                savedAnalysisId = savedAnalysisResult.getString("AnalysisId");
                parsedFlowsLabel.setText(savedAnalysisResult.getString("ParsedFlowsCount"));
                parsingErrorsLabel.setText(savedAnalysisResult.getString("ParsingErrorsCount"));
                totalPacketsLabel.setText(savedAnalysisResult.getString("TotalPacketsCount"));
            }
            
            // Clear out any existing data first
            sourcePortData.clear();
            destinationPortData.clear();
            sourceIpData.clear();
            destinationIpData.clear();
            
            // Load the Source Port Data          
            String sourcePortDataQuery = "select * from topsourceports where AnalysisId=" + savedAnalysisId;  
            ResultSet sourcePortResultSet = DatabaseUtil.ExecuteQuery(sourcePortDataQuery);
            
            while(sourcePortResultSet.next())
            {
                String port = sourcePortResultSet.getString("Port");
                Integer count = sourcePortResultSet.getInt("Count");
                
                sourcePortData.put(port, count);
            }
            
            // Load the Destination Port Data
            String destinationPortDataQuery = "select * from topdestinationports where AnalysisId=" + savedAnalysisId;        
            ResultSet destinationPortResultSet = DatabaseUtil.ExecuteQuery(destinationPortDataQuery);
            
            while(destinationPortResultSet.next())
            {
                String port = destinationPortResultSet.getString("Port");
                Integer count = destinationPortResultSet.getInt("Count");
                
                destinationPortData.put(port, count);
            }
            
            // Load the Source IP Data
            String sourceIpDataQuery = "select * from topsourceips where AnalysisId=" + savedAnalysisId;
            ResultSet sourceIpResultSet = DatabaseUtil.ExecuteQuery(sourceIpDataQuery);
            
            while(sourceIpResultSet.next())
            {
                String ip = sourceIpResultSet.getString("IP");
                Integer count = sourceIpResultSet.getInt("Count");
                
                sourceIpData.put(ip, count);
            }
            
            // Load the Destination IP Data
            String destinationIpDataQuery = "select * from topdestinationips where AnalysisId=" + savedAnalysisId;
            ResultSet destinationIpResultSet = DatabaseUtil.ExecuteQuery(destinationIpDataQuery);
            
            while(destinationIpResultSet.next())
            {
                String ip = destinationIpResultSet.getString("IP");
                Integer count = destinationIpResultSet.getInt("Count");
                
                destinationIpData.put(ip, count);
            }
            
            // Redraw the charts on the UI
            drawGeneralCharts(new PieChartStrategy());
            
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, resources.getString("error"), ex.toString());
        }
    }
    
    @FXML
    private void handleSaveSession(ActionEvent event)
    {
        // Confirm the user wishes to save the data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resources.getString("alertheader"));
        alert.setContentText(resources.getString("saveconfirmation"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL)
        {
            return;
        }      
        
        // Get a connection to the database
        Connection db = Database.GetInstance().GetConnection();
        
        try
        {
            // First we need to save a row in the savedanalysis table
            String timestamp = Utility.GenerateTimestamp();
            String savedAnalysesQuery = "insert into savedanalyses (AnalysisId, Date, ParsedFlowsCount, ParsingErrorsCount, TotalPacketsCount)"
                              + " values (NULL, '" + timestamp + "'," + parsedFlowsLabel.getText() + "," + parsingErrorsLabel.getText() + "," + totalPacketsLabel.getText() + ")";
            
            int analysisId = 0; // The auto-incremented primary key in the savedanalyses table - referenced when other metrics are saved
            
            PreparedStatement savedAnalysesStatement = db.prepareStatement(savedAnalysesQuery, Statement.RETURN_GENERATED_KEYS);      
            savedAnalysesStatement.executeUpdate();
            
            ResultSet rs = savedAnalysesStatement.getGeneratedKeys();
            
            if (rs.next()) 
            {
               analysisId = rs.getInt(1);   
            }
            
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
            
            LogUtil.Log(Alert.AlertType.INFORMATION, resources.getString("alertheader"), resources.getString("currentanalysissaved") + " '" + timestamp + "'"
                    + resources.getString("howtoload"));
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, resources.getString("error"), ex.toString());
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
    
    
    private void drawChartsInitial(DisplayStrategy strategy)
    {
        TopData sourcePorts = new TopSourcePorts();
        sourcePortData = sourcePorts.GetTopData(stats.GetFlows());
        
        TopData destinationPorts = new TopDestinationPorts();
        destinationPortData = destinationPorts.GetTopData(stats.GetFlows());
        
        TopData sourceIps = new TopSourceIpAddresses();
        sourceIpData = sourceIps.GetTopData(stats.GetFlows());
        
        TopData destinationIps = new TopDestinationIpAddresses();
        destinationIpData = destinationIps.GetTopData(stats.GetFlows());
        
        drawGeneralCharts(strategy);
    }
    
    
    private void drawGeneralCharts(DisplayStrategy strategy)
    {
        chartContext.setChartStrategy(strategy);
        
        topSourcePortsPane.getChildren().clear();
        topDestinationPortsPane.getChildren().clear();
        topSourceIPsPane.getChildren().clear();
        topDestinationIPsPane.getChildren().clear();
        
        // Top Source Ports
        Chart sourcePorts = chartContext.createChart(sourcePortData, resources.getString("topsourceports"));
        topSourcePortsPane.getChildren().add(sourcePorts);
        
        // Top Destination Ports
        Chart destinationPorts = chartContext.createChart(destinationPortData, resources.getString("topdestinationports"));
        topDestinationPortsPane.getChildren().add(destinationPorts);
        
        // Top Source IP Addresses
        Chart topSourceIPAddresses = chartContext.createChart(sourceIpData, resources.getString("topsourceipaddresses"));
        topSourceIPsPane.getChildren().add(topSourceIPAddresses);
        
        // Top Destination IP Addresses
        Chart topDestinationIPAddresses = chartContext.createChart(destinationIpData, resources.getString("topdestinationipaddresses"));
        topDestinationIPsPane.getChildren().add(topDestinationIPAddresses);
    }
     
    
    @FXML
    private void handleSecurityAnalysis(ActionEvent event)
    {
        DShieldApiProxy dshieldApi = new DShieldApiProxy();
        
        mainTabPane.getSelectionModel().select(1);
        
        // Load the threat level and display on the UI
        String threatLevel = dshieldApi.Infocon();
        
        switch(threatLevel.toUpperCase())
        {
            case "GREEN":
                threatLevelLabel.setText("GREEN");
                threatLevelLabel.setTextFill(Paint.valueOf("GREEN"));
                break;
            case "YELLOW":
                threatLevelLabel.setText("YELLOW");
                threatLevelLabel.setTextFill(Paint.valueOf("YELLOW"));
                break;
            case "ORANGE":
                threatLevelLabel.setText("ORANGE");
                threatLevelLabel.setTextFill(Paint.valueOf("ORANGE"));
                break;
            case "RED":
                threatLevelLabel.setText("RED");
                threatLevelLabel.setTextFill(Paint.valueOf("RED"));
                break;
        }
        
        // Create new threads using Lamda expressions to run security analysis (could use Anonymous Class here also)
        Thread sourceIpCheckingThread = new Thread(() -> 
        {
            // Test the Top Source IP's against the DShield API and display in the UI      
            PropertyValueFactory<DShieldIpInfo, String> sourceIpProperty = new PropertyValueFactory<>("IP");
            PropertyValueFactory<DShieldIpInfo, String> sourceBlockedProperty = new PropertyValueFactory<>("Blocked");
            PropertyValueFactory<DShieldIpInfo, String> sourceAttacksProperty = new PropertyValueFactory<>("Attacks");
            PropertyValueFactory<DShieldIpInfo, String> sourceCountryProperty = new PropertyValueFactory<>("Country");
            
            sourceIpColumn.setCellValueFactory(sourceIpProperty);        
            sourceIpBlockedColumm.setCellValueFactory(sourceBlockedProperty);
            sourceIpAttacksColumn.setCellValueFactory(sourceAttacksProperty);
            sourceIpCountryColumn.setCellValueFactory(sourceCountryProperty);
            
            sourceIpSecurityInfoTable.setItems(sourceIPSecurityInfoData);
                
            sourceIpData.entrySet().stream().forEach((entry) ->
            {
                DShieldIpInfo info = dshieldApi.Ip(entry.getKey());
                sourceIPSecurityInfoData.add(info);
            });
        });
        
        sourceIpCheckingThread.setDaemon(true);
        sourceIpCheckingThread.start();
        
        Thread destinationIpCheckingThread = new Thread(() -> 
        {        
            // Test the Top Destination IP's against the DShield API
            PropertyValueFactory<DShieldIpInfo, String> destinationIpProperty = new PropertyValueFactory<>("IP");
            PropertyValueFactory<DShieldIpInfo, String> destinationBlockedProperty = new PropertyValueFactory<>("Blocked");
            PropertyValueFactory<DShieldIpInfo, String> destinationAttacksProperty = new PropertyValueFactory<>("Attacks");
            PropertyValueFactory<DShieldIpInfo, String> destinationCountryProperty = new PropertyValueFactory<>("Country");
         
            destinationIpColumn.setCellValueFactory(destinationIpProperty);        
            destinationIpBlockedColumm.setCellValueFactory(destinationBlockedProperty);
            destinationIpAttacksColumn.setCellValueFactory(destinationAttacksProperty);
            destinationIpCountryColumn.setCellValueFactory(destinationCountryProperty);
            
            destinationIpSecurityInfoTable.setItems(destinationIPSecurityInfoData);
     
            destinationIpData.entrySet().stream().forEach((entry) ->
            {
                DShieldIpInfo info = dshieldApi.Ip(entry.getKey());
                destinationIPSecurityInfoData.add(info);
            });
        });
        
        destinationIpCheckingThread.setDaemon(true);
        destinationIpCheckingThread.start();
        
        Thread sourcePortCheckingThread = new Thread(() -> 
        {        
            // Test the Top Source Ports against the DShield API
            PropertyValueFactory<DShieldIpInfo, String> sourcePortProperty = new PropertyValueFactory<>("Port");
            PropertyValueFactory<DShieldIpInfo, String> sourceRecordsProperty = new PropertyValueFactory<>("Records");
            PropertyValueFactory<DShieldIpInfo, String> sourceTargetsProperty = new PropertyValueFactory<>("Targets");
            PropertyValueFactory<DShieldIpInfo, String> sourceSourcesProperty = new PropertyValueFactory<>("Sources");
         
            sourcePortColumn.setCellValueFactory(sourcePortProperty);        
            sourcePortRecordsColumn.setCellValueFactory(sourceRecordsProperty);
            sourcePortTargetsColumn.setCellValueFactory(sourceTargetsProperty);
            sourcePortSourcesColumn.setCellValueFactory(sourceSourcesProperty);
            
            sourcePortSecurityInfoTable.setItems(sourcePortSecurityInfoData);
     
            sourcePortData.entrySet().stream().forEach((entry) ->
            {
                DShieldPortInfo info = dshieldApi.Port(entry.getKey());
                sourcePortSecurityInfoData.add(info);
            });
        });
        
        sourcePortCheckingThread.setDaemon(true);
        sourcePortCheckingThread.start();
        
        Thread destinationPortCheckingThread = new Thread(() -> 
        {        
            // Test the Top Destination Ports against the DShield API
            PropertyValueFactory<DShieldIpInfo, String> destinationPortProperty = new PropertyValueFactory<>("Port");
            PropertyValueFactory<DShieldIpInfo, String> destinationRecordsProperty = new PropertyValueFactory<>("Records");
            PropertyValueFactory<DShieldIpInfo, String> destinationTargetsProperty = new PropertyValueFactory<>("Targets");
            PropertyValueFactory<DShieldIpInfo, String> destinationSourcesProperty = new PropertyValueFactory<>("Sources");
         
            destinationPortColumn.setCellValueFactory(destinationPortProperty);        
            destinationPortRecordsColumn.setCellValueFactory(destinationRecordsProperty);
            destinationPortTargetsColumn.setCellValueFactory(destinationTargetsProperty);
            destinationPortSourcesColumn.setCellValueFactory(destinationSourcesProperty);
            
            destinationPortSecurityInfoTable.setItems(destinationPortSecurityInfoData);
     
            destinationPortData.entrySet().stream().forEach((entry) ->
            {
                DShieldPortInfo info = dshieldApi.Port(entry.getKey());
                destinationPortSecurityInfoData.add(info);
            });
        });
        
        destinationPortCheckingThread.setDaemon(true);
        destinationPortCheckingThread.start();
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
