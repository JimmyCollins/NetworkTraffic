
package org.jimmycollins.networktraffic.util;

// TODO - Will be use for logging

import javafx.scene.control.Alert;


public class Logger {
    
    
    // TODO: Use to cover overloading requirement
    
    public static void Log(String message)
    {
        
    }
    
    public static void Log(String message, String exception)
    {
        
    }
    
    public static void Log(String mesage, String exception, String stacktrace)
    {
        
    }
    
    /**
     * Generates an alert to the user
     * @param type The Alert type
     * @param header The Alert header text
     * @param message The Alert message
     */
    public static void Log(Alert.AlertType type, String header, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle("Network Traffic Analyzer");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
    
    
    
}
