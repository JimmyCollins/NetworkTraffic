
package org.jimmycollins.networktraffic.model;

import java.util.Map;

/**
 * Defines an object that contains data to export
 */
public class DataExport 
{
    public final Map<String,Integer> SourcePortData;
    public final Map<String,Integer> DestinationPortData;
    public final Map<String,Integer> SourceIpData;
    public final Map<String,Integer> DestinationIpData;
    
    public DataExport(Map<String,Integer> sourcePorts, Map<String,Integer> destinationPorts, Map<String,Integer> sourceIps,
            Map<String,Integer> destinationIps)
    {
        SourcePortData = sourcePorts;
        DestinationPortData = destinationPorts;
        SourceIpData = sourceIps;
        DestinationIpData = destinationIps;
        
        // TODO: Add Security Analysis data to export reports?
    }
}