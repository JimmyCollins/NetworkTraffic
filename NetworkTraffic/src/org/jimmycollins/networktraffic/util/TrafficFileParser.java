package org.jimmycollins.networktraffic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;
import org.jimmycollins.networktraffic.ParsingException;
import org.jimmycollins.networktraffic.model.TrafficFileStats;


public class TrafficFileParser {
    
    //private final File TrafficFile;
    
    //public ArrayList<NetworkPacket> RubbishPackets;
    
    public TrafficFileParser() {
        //this.TrafficFile = file;
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
    

    
    
    
    public static TrafficFileStats ParseFile(File file) 
    {   
        TrafficFileStats stats = new TrafficFileStats();
        
        try
        {    
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine(); 
            String line = null;
            while ((line = reader.readLine()) != null)
            {             
               String[] fields = line.split(",");
               
               // Could pass line to analyzer here to be processed, which
               // would update a TrafficFileStats Object?
               
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
                    

                    
                    stats.AddSourceHost(fields[3]);
                    stats.AddDestinationHost(fields[6]);
                    stats.AddSourcePort(fields[4]);
                    stats.AddDestinationPort(fields[7]);
                    stats.AddPacket();

                    // TODO - Do we need the np object here?
                    // TODO: Add other stats - e.g. IP's etc.?
                    
                    //list.add(np);
               }
               catch(Exception ex)  // TODO - Create custom exception
               {
                   stats.AddUnparsablePacket(); // TODO: Add to UI
                   //Utility.Alert(AlertType.ERROR, "Error", ex.toString());
                   // TODO: Log exception (have log tab on UI?)
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