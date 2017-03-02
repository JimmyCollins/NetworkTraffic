package org.jimmycollins.networktraffic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;
import org.jimmycollins.networktraffic.ParsingException;
import org.jimmycollins.networktraffic.model.FlowFileStats;
//import org.jimmycollins.networktraffic.model.ParsingObserver;


public class FlowFileParser {
    
    
    private File theFile;
    
    public FlowFileParser(File file)
    {
        theFile = file;
    }
    
    
    /**
     * 
     * @param file
     * @return 
     */ 
    public FlowFileStats ParseFile(FlowFileStats stats) 
    {   
        //FlowFileStats stats = new FlowFileStats();
        //ParsingObserver p = new ParsingObserver(stats);
        
        try
        {    
            BufferedReader reader = new BufferedReader(new FileReader(theFile));
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
                    

                    // Get rid of adds and use Observable List??
                   

                    stats.AddSourceHost(Utility.ParseInetAddress(fields[3]));
                    stats.AddDestinationHost(Utility.ParseInetAddress(fields[6]));
                    stats.AddSourcePort(Utility.ParseInt(fields[4]));
                    stats.AddDestinationPort(Utility.ParseInt(fields[7]));
                    stats.AddProtocol(fields[2]);
                    stats.AddFlow();
                    
                    // TODO: Add other stats - e.g. IP's etc.?
                    
                    //list.add(np);
               }
               catch(Exception ex)  // TODO - Create custom exception
               {
                   stats.AddUnparsableFlow(); // TODO: Add to UI
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