package org.jimmycollins.networktraffic.util;

import org.jimmycollins.networktraffic.model.NetworkPacket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


public class TrafficFileParser {
    
    private final File TrafficFile;
    
    public TrafficFileParser(File file) {
        this.TrafficFile = file;
    }
    
    public ArrayList<NetworkPacket> ParseFile() {
        
        ArrayList list = new ArrayList<>();
        
        try
        {    
            BufferedReader reader = new BufferedReader(new FileReader(TrafficFile));
            reader.readLine(); 
            String line = null;
            while ((line = reader.readLine()) != null)
            { 
               // FIXME: Ignore first line of the file
               // FIXME: Handle errors
                
               String[] fields = line.split(",");
               
               NetworkPacket np = new NetworkPacket();
               np.setDate((fields[0]));
               np.setDuration(Float.parseFloat(fields[1]));
               np.setProtocol(fields[2]);
               np.setSourceIP(InetAddress.getByName(fields[3]));
               np.setSourcePort(Integer.parseInt(fields[4]));
               np.setDirection(fields[5]);
               np.setDestinationIP(InetAddress.getByName(fields[6]));
               np.setDestinationPort(Integer.parseInt(fields[7]));
               np.setState(fields[8]);
               np.setSourceTypeOfService(Integer.parseInt(fields[9]));
               np.setDestinationTypeOfService(Integer.parseInt(fields[10]));
               np.setTotalPackets(Integer.parseInt(fields[11]));
               np.setTotalBytes(Integer.parseInt(fields[12]));
               np.setSourceBytes(Integer.parseInt(fields[13]));
               np.setLabel(fields[14]);
               
               list.add(np);     
            }
            
            /*BufferedReader br = new BufferedReader(new FileReader(TrafficFile));
            String line;
            while ((line = br.readLine()) != null) 
            {
     
            }*/
        }
        catch(FileNotFoundException ex)
        {
            // TODO: Log exception
        }
        catch(IOException ex)
        {
            // TODO: Log exception
        }
        
        return list;
    }
}
