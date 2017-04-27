
package org.jimmycollins.networktraffic.model;

import java.util.Map;

/**
 * Defines an object that contains data to export
 * Used for the export functionality
 */
public class DataExport 
{
    public final Map<String,Integer> SourcePortData;
    
    public final Map<String,Integer> DestinationPortData;
    
    public final Map<String,Integer> SourceIpData;
    
    public final Map<String,Integer> DestinationIpData;
    
    /**
     * Constructor
     * @param sourcePorts The data for source ports
     * @param destinationPorts The data for destination ports
     * @param sourceIps The data for source IP addresses
     * @param destinationIps The data for destination IP addresses
     */
    public DataExport(Map<String,Integer> sourcePorts, Map<String,Integer> destinationPorts, Map<String,Integer> sourceIps,
            Map<String,Integer> destinationIps)
    {
        SourcePortData = sourcePorts;
        DestinationPortData = destinationPorts;
        SourceIpData = sourceIps;
        DestinationIpData = destinationIps;
    }
}