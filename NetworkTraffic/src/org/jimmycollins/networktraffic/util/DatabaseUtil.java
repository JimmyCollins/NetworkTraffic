
package org.jimmycollins.networktraffic.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class DatabaseUtil 
{
     
    public static ResultSet ExecuteQuery(String query)  throws IOException
    {
        Connection db = Database.getInstance().getConnection();
        
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
}
