
package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    
    public static String getMostUsedIpAddress(List<String> ipAddress)
    {
        Collections.sort(ipAddress); 
        String temp = ""; 
        String mostCommon = "";
        
        int max = 0;
        int num = 0;
        for (String string : ipAddress) 
        {
          if (string.equals(temp)) 
          {
            num++;
          } 
          else {
            if (num>max) 
            {
              max = num;
              mostCommon = string;
            }
            num = 1;
            temp = string;
          }
        }
        return mostCommon;
}
    
}
