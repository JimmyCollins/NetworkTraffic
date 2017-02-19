
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class PacketFileStats 
{
    
    //private static int NumberOfPackets = 0;
    
    //private static int NumberOfRubbishPackets = 0;
    
    private static ArrayList<String> SourceHosts;
    
    private static ArrayList<String> DestinationHosts;
    
    private static ArrayList<Integer> SourcePorts;
    
    private static ArrayList<Integer> DestinationPorts;
    
    
    public PacketFileStats()
    {
        SourceHosts = new ArrayList<>();
        DestinationHosts = new ArrayList<>();
        SourcePorts = new ArrayList<>();
        DestinationPorts = new ArrayList<>();
    }
    
    /*public void AddPacket()
    {
        NumberOfPackets++;
    }
    
    
    public void AddToNumberOfRubbishPackets()
    {
        NumberOfRubbishPackets++;
    }*/
    
    public void AddSourceHost(String host)
    {
        SourceHosts.add(host);
    }
    
    public void AddDestinationHost(String host)
    {
        DestinationHosts.add(host);
    }
    
    public void AddSourcePort(int port)
    {
        SourcePorts.add(port);
    }
    
    public void AddDestinationPort(int port)
    {
        DestinationPorts.add(port);
    }
    
    /*public int GetNumberOfPackets()
    {
        return NumberOfPackets;
    }
    
    public int GetNumberOfRubbishPackets()
    {
        return NumberOfRubbishPackets;
    }*/
    
    public ArrayList<String> GetSourceHosts()
    {
        return SourceHosts;
    }
    
    public ArrayList<String> GetDestinationHosts()
    {
        return DestinationHosts;
    }
    
}
