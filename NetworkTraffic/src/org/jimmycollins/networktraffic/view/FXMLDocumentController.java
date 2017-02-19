
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.model.PacketFileStats;
import org.jimmycollins.networktraffic.util.TrafficFileParser;
import org.jimmycollins.networktraffic.util.Utility;

public class FXMLDocumentController implements Initializable 
{   
    @FXML
    private PieChart top5SourceIpPie;
    
    @FXML
    private PieChart top5DestIpPie;
    
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
            //goodPackets.setText(""+stats.GetNumberOfPackets());
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Rubbish Packet Count: " + stats.GetNumberOfRubbishPackets());
            //badPackets.setText(""+stats.GetNumberOfRubbishPackets());
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Total src IP Count: " + stats());
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Rubbish dest IP Count: " + stats.GetNumberOfRubbishPackets());
            
            //Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Most Common Source IP: " + Utility.getMostUsedIpAddress(stats.GetSourceHosts()));
            //mostCommonSourceIP.setText(""+Utility.getMostUsedIpAddress(stats.GetSourceHosts()));
            
            // Pie Chart - parsed packets
            /*PieChart.Data good = new PieChart.Data("Parsed", stats.GetNumberOfPackets());
            PieChart.Data bad = new PieChart.Data("Unparsable", stats.GetNumberOfRubbishPackets());          
            packetOverviewChart.getData().add(good);
            packetOverviewChart.getData().add(bad);*/
            
            
            // Pie Chart - most common source IPs
            Map<String,Integer> map = Utility.GetTopElements(stats.GetSourceHosts(), 5);
            
            map.entrySet().stream().map((entry) -> new PieChart.Data(entry.getKey(), entry.getValue())).forEachOrdered((test) -> 
            {
                top5SourceIpPie.getData().add(test);
            });
            
            // Pie Chart - most common source IPs
            Map<String,Integer> map2 = Utility.GetTopElements(stats.GetDestinationHosts(), 5);
            
            map2.entrySet().stream().map((entry) -> new PieChart.Data(entry.getKey(), entry.getValue())).forEachOrdered((test) -> 
            {
                top5DestIpPie.getData().add(test);
            }); //XYChart.Series series1 = new XYChart.Series();
            
            
            
        }
        catch(Exception ex)
        {
            
        }
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
