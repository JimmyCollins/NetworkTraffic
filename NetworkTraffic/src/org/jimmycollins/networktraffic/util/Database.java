
package org.jimmycollins.networktraffic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;

/**
 * Database connection class - implements a Singleton Pattern
 */
public class Database 
{
    private static Database Database;
    private static Connection DbConnection;
    
    private final Locale locale = new Locale("en", "US");
    private final ResourceBundle resources = ResourceBundle.getBundle("ResourcesBundle", locale);

    /**
     * Get an instance of the database connection
     * @return The database connection instance
     */
    public static Database GetInstance()
    {
        if(Database == null)
        {
            Database = new Database();
        }
        
        return Database;
    }
    
    /**
     * Get a connection to the database
     * @return A connection to the database
     */
    public Connection GetConnection()
    {
        if(DbConnection == null)
        {
            try 
            {
                String host = "jdbc:mysql://localhost:3306/networktraffic";
                String username = "root";
                String password = "root";
                DbConnection = DriverManager.getConnection(host, username, password);
            } 
            catch (SQLException ex) 
            {
                LogUtil.Log(LogUtil.LogLevel.SEVERE, ex.toString());
                LogUtil.Log(Alert.AlertType.ERROR, resources.getString("alertheader"), resources.getString("dbconnectionerror") + ex.getMessage());
            }
        }

        return DbConnection;
    }
}
