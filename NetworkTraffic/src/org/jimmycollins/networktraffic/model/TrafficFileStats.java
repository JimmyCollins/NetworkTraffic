
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class TrafficFileStats 
{    
    private static int ParsedPackets = 0;
    
    private static int UnparsablePackets;
    
    private static ArrayList<String> SourceHosts;
    
    private static ArrayList<String> DestinationHosts;
    
    private static ArrayList<Integer> SourcePorts;
    
    private static ArrayList<Integer> DestinationPorts;
    
    
    public TrafficFileStats()
    {
        ParsedPackets = 0;
        UnparsablePackets = 0;
        SourceHosts = new ArrayList<>();
        DestinationHosts = new ArrayList<>();
        SourcePorts = new ArrayList<>();
        DestinationPorts = new ArrayList<>();
    }
    
    public void AddPacket()
    {
        ParsedPackets++;
    }
    
    public void AddUnparsablePacket()
    {
        UnparsablePackets++;
    }
    
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
    
    public ArrayList<String> GetSourceHosts()
    {
        return SourceHosts;
    }
    
    public ArrayList<String> GetDestinationHosts()
    {
        return DestinationHosts;
    }
    
    public int GetParsedPackets()
    {
        return ParsedPackets;
    }
    
    public int GetUnparsablePackets()
    {
        return UnparsablePackets;
    }
    
}
