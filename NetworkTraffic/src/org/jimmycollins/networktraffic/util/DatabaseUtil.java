package org.jimmycollins.networktraffic.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javafx.scene.control.Alert;

/**
 * This facade class is a wrapper that contains a set of members that are used for database operations that are easily understood and simple to use.
 * These members access the database on behalf of the facade user, hiding the implementation details.
 */

public class DatabaseUtil 
{
    /**
     * Execute a query against the database
     * @param query The query to execute
     * @return The ResultSet of the query executed
     * @throws IOException if something goes wrong
     */
    public static ResultSet Query(String query) throws IOException
    {
        Connection db = Database.GetInstance().GetConnection();
        
        try
        {   
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Error", ex.toString());
            return null;
        }
    }
    
    
    // return // The auto-incremented primary key in the savedanalyses table - referenced when other metrics are saved
    public static int PersistAnalysisRecord(String query)
    {
        Connection db = Database.GetInstance().GetConnection();
        
        int analysisId = 0;
        
        try
        {
        PreparedStatement savedAnalysesStatement = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);      
        savedAnalysesStatement.executeUpdate();

        ResultSet rs = savedAnalysesStatement.getGeneratedKeys();

        if (rs.next()) 
        {
           analysisId = rs.getInt(1);   
        }
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Error", ex.toString());
            return analysisId;
        }
        
        return analysisId;
    }
    
    
    public static void PersistPortData(int analysisId, String table, Map<String,Integer> data)
    {
        Connection db = Database.GetInstance().GetConnection();
        
        try
        {   
            for (Map.Entry<String, Integer> entry : data.entrySet())
            {
                String key = entry.getKey();
                Integer value = entry.getValue();
                
                String query = "insert into " + table + " (Id, AnalysisId, Port, Count)"
                              + " values (NULL, " + analysisId + "," + key + "," + value + ")";
                
                PreparedStatement sourcePortStatement = db.prepareStatement(query);      
                sourcePortStatement.executeUpdate();
            }
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Error", ex.toString());
        }
    }
    
    
    public static void PersistIpData(int analysisId, String table, Map<String,Integer> data)
    {
        Connection db = Database.GetInstance().GetConnection();
        
        try
        {   
            for (Map.Entry<String, Integer> entry : data.entrySet())
            {
                String key = entry.getKey();
                Integer value = entry.getValue();
                
                String query = "insert into " + table + " (Id, AnalysisId, IP, Count)"
                              + " values (NULL, " + analysisId + "," + key + "," + value + ")";
                
                PreparedStatement sourcePortStatement = db.prepareStatement(query);      
                sourcePortStatement.executeUpdate();
            }
        }
        catch(SQLException ex)
        {
            LogUtil.Log(Alert.AlertType.ERROR, "Error", ex.toString());
        }
    }
    
}
