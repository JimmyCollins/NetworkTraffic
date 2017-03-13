
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;

/**
 * Represents a single flow parsed from the file
 * @author Jimmy Collins
 */
public class Flow {
    
    private InetAddress SourceHost;
    
    private InetAddress DestinationHost;
    
    private int SourcePort;
    
    private int DestinationPort;
    
    private String Protocol;
    
    public Enum FlowType;
    
    /**
     * Constructor
     * @param type The type (based on the network protocol) of this flow
     */
    public Flow(Enum type)
    {
        this.FlowType = type;
    }
       
    public void SetSourceHost(InetAddress host)
    {
        this.SourceHost = host;
    }
    
    public void SetDestinationHost(InetAddress host)
    {
        this.DestinationHost = host;
    }
    
    public void SetSourcePort(int port)
    {
        this.SourcePort = port;
    }
    
    public void SetDestinationPort(int port)
    {
        this.DestinationPort = port;
    }
    
    public void SetProtocol(String protocol)
    {
        this.Protocol = protocol;
    }
    
    public InetAddress GetSourceHost()
    {
        return SourceHost;
    }
    
    public InetAddress GetDestinationHost()
    {
        return DestinationHost;
    }
    
    public int GetSourcePort()
    {
        return SourcePort;
    }
    
    public int GetDestinationPort()
    {
        return DestinationPort;
    }
    
    public String GetProtocol()
    {
        return Protocol;
    }
    
    public String GetFlowType()
    {
        return FlowType.toString();
    }
}