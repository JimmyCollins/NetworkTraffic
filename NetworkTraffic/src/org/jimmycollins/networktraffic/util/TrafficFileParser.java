package org.jimmycollins.networktraffic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.jimmycollins.networktraffic.model.NetworkPacket;
import org.jimmycollins.networktraffic.model.PacketFileStats;


public class TrafficFileParser {
    
    private final File TrafficFile;
    
    //public ArrayList<NetworkPacket> RubbishPackets;
    
    public TrafficFileParser(File file) {
        this.TrafficFile = file;
        //this.RubbishPackets = new ArrayList<>();
    }
    
    
    // TODO: File is too big to parse, need to rethink this.
    
    /*public ArrayList<NetworkPacket> ParseFile() {
        
        ArrayList list = new ArrayList<>();
        
        try
        {    
            BufferedReader reader = new BufferedReader(new FileReader(TrafficFile));
            reader.readLine(); 
            String line = null;
            while ((line = reader.readLine()) != null)
            { 
               // FIXME: Handle errors
                
               String[] fields = line.split(",");
               
               NetworkPacket np = new NetworkPacket();
               np.setDate((fields[0]));
               //np.setDuration(Float.parseFloat(fields[1]));
               np.setDuration(fields[1]);
               np.setProtocol(fields[2]);
               //np.setSourceIP(InetAddress.getByName(fields[3]));
               np.setSourceIP(fields[3]);
               //np.setSourcePort(Integer.parseInt(fields[4]));
               np.setSourcePort(fields[4]);
               np.setDirection(fields[5]);
               //np.setDestinationIP(InetAddress.getByName(fields[6]));
               np.setDestinationIP(fields[6]);
               //np.setDestinationPort(Integer.parseInt(fields[7]));
               np.setDestinationPort(fields[7]);
               np.setState(fields[8]);
               //np.setSourceTypeOfService(Integer.parseInt(fields[9]));
               //np.setDestinationTypeOfService(Integer.parseInt(fields[10]));
               //np.setTotalPackets(Integer.parseInt(fields[11]));
               //np.setTotalBytes(Integer.parseInt(fields[12]));
               //np.setSourceBytes(Integer.parseInt(fields[13]));
               //np.setSourceTypeOfService(Integer.parseInt(fields[9]));
               np.setDestinationTypeOfService(fields[10]);
               np.setTotalPackets(fields[11]);
               np.setTotalBytes(fields[12]);
               np.setSourceBytes(fields[13]);
               np.setLabel(fields[14]);
               
               list.add(np);     
            }
            

        }
        catch(FileNotFoundException ex)
        {
            // TODO: Log exception
            Utility.Alert(AlertType.ERROR, "FileNotFoundException", ex.toString());
        }
        catch(IOException ex)
        {
            // TODO: Log exception
            Utility.Alert(AlertType.ERROR, "IOException", ex.toString());
        }
        catch(Exception ex)
        {
            // TODO: Log exception
            Utility.Alert(AlertType.ERROR, "Exception", ex.toString());
            
            
        }
        
        return list;
    }*/
    

    
    
    
    public PacketFileStats ParseFile() 
    {      
        //ArrayList list = new ArrayList<>();
        PacketFileStats stats = new PacketFileStats();
        
        try
        {    
            BufferedReader reader = new BufferedReader(new FileReader(TrafficFile));
            reader.readLine(); 
            String line = null;
            while ((line = reader.readLine()) != null)
            {             
               String[] fields = line.split(",");
               
               try
               {
                    /*NetworkPacket np = new NetworkPacket();
                    np.setDate((fields[0]));
                    np.setDuration(Float.parseFloat(fields[1]));
                    np.setProtocol(fields[2]);
                    np.setSourceIP(fields[3]);
                    np.setSourcePort(Integer.parseInt(fields[4]));
                    np.setDirection(fields[5]);
                    np.setDestinationIP(fields[6]);
                    np.setDestinationPort(Integer.parseInt(fields[7]));
                    np.setState(fields[8]);
                    np.setSourceTypeOfService(Integer.parseInt(fields[9]));
                    np.setDestinationTypeOfService(Integer.parseInt(fields[10]));
                    np.setTotalPackets(Integer.parseInt(fields[11]));
                    np.setTotalBytes(Integer.parseInt(fields[12]));
                    np.setSourceBytes(Integer.parseInt(fields[13]));
                    np.setLabel(fields[14]);*/           
                    
                    stats.AddToNumberOfPackets();
                    stats.AddNewSourceHost(fields[3]);
                    stats.AddNewDestinationHost(InetAddress.getByName(fields[6]));

                    // TODO - Do we need the np object here?
                    // TODO: Add other stats - e.g. IP's etc.?
                    
                    //list.add(np);     
               }
               catch(Exception ex)  // TODO - Create custom exception
               {
                   stats.AddToNumberOfRubbishPackets();
               }
               
            }        
        }
        catch(FileNotFoundException ex)
        {
            // TODO: Log exception
            Utility.Alert(AlertType.ERROR, "FileNotFoundException", ex.toString());
        }
        catch(IOException ex)
        {
            // TODO: Log exception
            Utility.Alert(AlertType.ERROR, "IOException", ex.toString());
        }
        
        return stats;
    }
    
}