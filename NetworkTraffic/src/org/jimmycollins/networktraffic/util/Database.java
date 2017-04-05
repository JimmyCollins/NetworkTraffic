package org.jimmycollins.networktraffic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;


// Ref: http://stackoverflow.com/questions/6567839/using-a-singleton-class-for-database-connection
/// Getting a connection - Connection con = Database.getInstance().getConnection();

public class Database 
{
    private static Database Db;
    private static Connection DbConnection;
    //private static Statement stmt;
    
    
    private Database() 
    {
        
    }

    public static Database getInstance()
    {
        if(Db == null)
        {
            Db = new Database();
        }
        
        return Db;
    }
    
    
    public Connection getConnection()
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
                LogUtil.Log(LogUtil.LogLevel.SEVERE, ex.getMessage());
            }
        }

        return DbConnection;
    }
    
}
