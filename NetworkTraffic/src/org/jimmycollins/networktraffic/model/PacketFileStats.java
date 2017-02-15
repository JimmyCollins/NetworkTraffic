
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class PacketFileStats 
{
    
    private static int NumberOfPackets = 0;
    
    private static int NumberOfRubbishPackets = 0;
    
    private static List<String> SourceHosts = new ArrayList<>();
    
    private static List<InetAddress> DestinationHosts = new ArrayList<>();
    
    
    public PacketFileStats()
    {
        //this.NumberOfPackets = 0;
        //this.NumberOfRubbishPackets = 0;
        //this.SourceIP = new ArrayList<>();
        //this.DestinationIP = new ArrayList<>();
    }
    
    public void AddToNumberOfPackets()
    {
        NumberOfPackets++;
    }
    
    
    public void AddToNumberOfRubbishPackets()
    {
        NumberOfRubbishPackets++;
    }
    
    public void AddNewSourceHost(String host)
    {
        SourceHosts.add(host);
    }
    
    public void AddNewDestinationHost(InetAddress host)
    {
        DestinationHosts.add(host);
    }
    
    public int GetNumberOfPackets()
    {
        return NumberOfPackets;
    }
    
    public int GetNumberOfRubbishPackets()
    {
        return NumberOfRubbishPackets;
    }
    
    public List<String> GetSourceHosts()
    {
        return SourceHosts;
    }
    
}
