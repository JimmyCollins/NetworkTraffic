
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class PacketFileStats 
{
    
    private static int NumberOfPackets = 0;
    
    private static int NumberOfRubbishPackets = 0;
    
    private static ArrayList<String> SourceHosts = new ArrayList<>();
    
    private static ArrayList<InetAddress> DestinationHosts = new ArrayList<>();
    
    private static ArrayList<Integer> SourcePorts = new ArrayList<>();
    
    private static ArrayList<Integer> DestinationPorts = new ArrayList<>();
    
    
    public PacketFileStats()
    {
        //this.NumberOfPackets = 0;
        //this.NumberOfRubbishPackets = 0;
        //this.SourceIP = new ArrayList<>();
        //this.DestinationIP = new ArrayList<>();
    }
    
    public void AddPacket()
    {
        NumberOfPackets++;
    }
    
    
    public void AddToNumberOfRubbishPackets()
    {
        NumberOfRubbishPackets++;
    }
    
    public void AddSourceHost(String host)
    {
        SourceHosts.add(host);
    }
    
    public void AddDestinationHost(InetAddress host)
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
    
    public int GetNumberOfPackets()
    {
        return NumberOfPackets;
    }
    
    public int GetNumberOfRubbishPackets()
    {
        return NumberOfRubbishPackets;
    }
    
    public ArrayList<String> GetSourceHosts()
    {
        return SourceHosts;
    }
    
}
