
package org.jimmycollins.networktraffic.util;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * Utility class for logging
 */
public class LogUtil {
    
    private static final Logger LOGGER = Logger.getLogger("com.jimmycollins.networktraffic");
    private static Boolean LOGGER_INITIALIZED = false;
    
    private static FileHandler handler;
    
    private static final Locale locale = new Locale("en", "US");
    private static final ResourceBundle resources = ResourceBundle.getBundle("ResourcesBundle", locale);
    
    public enum LogLevel {
	INFO, WARN, SEVERE
    }
    
    /**
     * Initialize the log
     */
    public static void Initialize()
    {
        try
        {
            handler = new FileHandler("networktraffic.log", true);
            LOGGER.addHandler(handler);
            LOGGER_INITIALIZED = true;
        }
        catch(IOException | SecurityException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resources.getString("alertheader"));
            alert.setContentText(resources.getString("loggingerror"));
            alert.show();
        }
    }
       
    /**
     * Log a message to the log file
     * @param level The log level
     * @param message The message to be logged
     */
    public static void Log(LogLevel level, String message)
    {   
        if(!LOGGER_INITIALIZED)
        {
            Initialize();
        }
        
        switch(level)
        {
            case INFO:
                LOGGER.info(message);
                break;
            case WARN:
                LOGGER.warning(message);
                break;
            case SEVERE:
                LOGGER.severe(message);
                break;
            default:
                LOGGER.info(message);
                break;
        }
    }
      
    /**
     * Generates a log alert to the user
     * @param type The Alert type
     * @param header The Alert header text
     * @param message The Alert message
     */
    public static void Log(Alert.AlertType type, String header, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(resources.getString("alertheader"));
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
}
