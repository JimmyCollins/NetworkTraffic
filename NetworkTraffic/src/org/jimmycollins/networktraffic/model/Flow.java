
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;

/**
 * Represents a single flow parsed from the file
 */
public class Flow {
    
    private InetAddress SourceHost;
    
    private InetAddress DestinationHost;
    
    private int SourcePort;
    
    private int DestinationPort;
    
    public Enum FlowType;
    
    /**
     * Constructor
     * @param type The type (based on the network protocol) of this flow
     */
    public Flow(Enum type)
    {
        this.FlowType = type;
    }
    
    /**
     * Set the source host
     * @param host The source host
     */
    public void SetSourceHost(InetAddress host)
    {
        this.SourceHost = host;
    }
    
    /**
     * Set the destination host
     * @param host The destination host
     */
    public void SetDestinationHost(InetAddress host)
    {
        this.DestinationHost = host;
    }
    
    /**
     * Set the source port
     * @param port The source port
     */
    public void SetSourcePort(int port)
    {
        this.SourcePort = port;
    }
    
    /**
     * Set the destination port
     * @param port The destination port
     */
    public void SetDestinationPort(int port)
    {
        this.DestinationPort = port;
    }
    
    /**
     * Get the source host
     * @return The source host
     */
    public InetAddress GetSourceHost()
    {
        return SourceHost;
    }
    
    /**
     * Get the destination host
     * @return The destination host
     */
    public InetAddress GetDestinationHost()
    {
        return DestinationHost;
    }
    
    /**
     * Get the source port
     * @return The source port
     */
    public int GetSourcePort()
    {
        return SourcePort;
    }
    
    /**
     * Get the destination port
     * @return The destination port
     */
    public int GetDestinationPort()
    {
        return DestinationPort;
    }
    
    /**
     * Get the flow type
     * @return The flow type
     */
    public String GetFlowType()
    {
        return FlowType.toString();
    }
}