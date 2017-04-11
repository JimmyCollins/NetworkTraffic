package org.jimmycollins.networktraffic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 * Database connection class
 * Utilizes the Singleton Pattern
 */
public class Database 
{
    private static Database Database;
    private static Connection DbConnection;

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
                LogUtil.Log(Alert.AlertType.ERROR, "Network Traffic Analyzer", "Could not connect to database: \n" + ex.getMessage());
            }
        }

        return DbConnection;
    }
    
}
