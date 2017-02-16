
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.model.NetworkPacket;
import org.jimmycollins.networktraffic.model.PacketFileStats;
import org.jimmycollins.networktraffic.util.TrafficFileParser;
import org.jimmycollins.networktraffic.util.Utility;

public class FXMLDocumentController implements Initializable 
{
    @FXML
    private Label goodPackets;
    
    @FXML
    private Label badPackets;
    
    @FXML
    private Label mostCommonSourceIP;
    
    @FXML
    private PieChart packetOverviewChart;
    
    @FXML
    private void handleSelectTrafficFile(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("binetflow files (*.binetflow)", "*.binetflow");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        
        TrafficFileParser tfp = new TrafficFileParser(file);
        
        try
        {
            //ArrayList<NetworkPacket> trafficInfo =  tfp.ParseFile();
            
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Parsed " + trafficInfo.size() + " packets from file.");
            
            PacketFileStats stats = tfp.ParseFile();
            
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Good Packet Count: " + stats.GetNumberOfPackets());
            goodPackets.setText(""+stats.GetNumberOfPackets());
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Rubbish Packet Count: " + stats.GetNumberOfRubbishPackets());
            badPackets.setText(""+stats.GetNumberOfRubbishPackets());
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Total src IP Count: " + stats());
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Rubbish dest IP Count: " + stats.GetNumberOfRubbishPackets());
            
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Most Common Source IP: " + Utility.getMostUsedIpAddress(stats.GetSourceHosts()));
            mostCommonSourceIP.setText(""+Utility.getMostUsedIpAddress(stats.GetSourceHosts()));
            
            PieChart.Data good = new PieChart.Data("Parsed", stats.GetNumberOfPackets());
            PieChart.Data bad = new PieChart.Data("Unparsable", stats.GetNumberOfRubbishPackets());
            
            packetOverviewChart.getData().add(good);
            packetOverviewChart.getData().add(bad);
            
        }
        catch(Exception ex)
        {
            
        }
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
