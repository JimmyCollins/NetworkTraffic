
package org.jimmycollins.networktraffic.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jimmycollins.networktraffic.model.NetworkPacket;
import org.jimmycollins.networktraffic.util.TrafficFileParser;
import org.jimmycollins.networktraffic.util.Utility;

public class FXMLDocumentController implements Initializable {
    
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
            ArrayList<NetworkPacket> trafficInfo =  tfp.ParseFile2();
            
            Utility.Alert(AlertType.INFORMATION, "Parsed File Successfully", "Parsed " + trafficInfo.size() + " packets from file.");
            
        }
        catch(Exception ex)
        {
            
        }
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
