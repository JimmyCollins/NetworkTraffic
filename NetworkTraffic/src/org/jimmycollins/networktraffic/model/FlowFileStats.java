
package org.jimmycollins.networktraffic.model;

import java.util.ArrayList;

public class FlowFileStats 
{    
    private static int ParsedFlows = 0;
    
    private static int UnparsableFlows;
    
    private static ArrayList<String> SourceHosts;  // Should be
    
    private static ArrayList<String> DestinationHosts;
    
    private static ArrayList<String> SourcePorts;
    
    private static ArrayList<String> DestinationPorts;
    
    private static ArrayList<String> Protocols;
    
    // This could have the top five things stored and created here??
    
    // And a set of gets that return them as <String,Integer> maps?
    
    /**
     * Potential steps to fix the data model:
     * 
     * 1. Make things the proper types they should be here
     * 2. Fix the parser so we try to parse things as their types
     * 3. Add functions here to get the top 5's in <String,Integer> maps (remove these calls from the controller)
    */
    
    
    
    public FlowFileStats()
    {
        ParsedFlows = 0;
        UnparsableFlows = 0;
        SourceHosts = new ArrayList<>();
        DestinationHosts = new ArrayList<>();
        SourcePorts = new ArrayList<>();
        DestinationPorts = new ArrayList<>();
        Protocols = new ArrayList();
    }
    
    public void AddFlow()
    {
        ParsedFlows++;
    }
    
    public void AddUnparsableFlow()
    {
        UnparsableFlows++;
    }
    
    public void AddSourceHost(String host)
    {
        SourceHosts.add(host);
    }
    
    public void AddDestinationHost(String host)
    {
        DestinationHosts.add(host);
    }
    
    public void AddSourcePort(String port)
    {
        SourcePorts.add(port);
    }
    
    public void AddDestinationPort(String port)
    {
        DestinationPorts.add(port);
    }
    
    public void AddProtocol(String protocol)
    {
        Protocols.add(protocol);
    }
    
    public ArrayList<String> GetSourceHosts()
    {
        return SourceHosts;
    }
    
    public ArrayList<String> GetDestinationHosts()
    {
        return DestinationHosts;
    }
    
    public ArrayList<String> GetSourcePorts()
    {
        return SourcePorts;
    }
    
    public ArrayList<String> GetDestinationPorts()
    {
        return DestinationPorts;
    }
    
    public int GetParsedFlows()
    {
        return ParsedFlows;
    }
    
    public int GetUnparsableFlows()
    {
        return UnparsableFlows;
    }
    
    public ArrayList<String> GetProtocols()
    {
        return Protocols;
    }
    
}
