
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

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private void handleSelectTrafficFile(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("binetflow files (*.binetflow)", "*.binetflow");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        
        TrafficFileParser tfp = new TrafficFileParser(file);
            
        ArrayList<NetworkPacket> trafficInfo =  tfp.ParseFile();
        
        // FIXME: Remove this
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Network Traffic");
        alert.setHeaderText("Parsed File Successfully");
        alert.setContentText("Parsed " + trafficInfo.size() + " packets from file.");
        alert.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
