
package org.jimmycollins.networktraffic.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Utility {
    
    public static void Alert(AlertType type, String header, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle("Network Traffic Analyzer");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
    
}
