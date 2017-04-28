package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Map;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.TopData;

/**
 * Part of Template Pattern Implementation
 */
public class TopSourceIpAddresses extends TopData
{
    private ArrayList SourceIpAddresses;

    /**
     * Initialize the data
     */
    @Override
    public void Initialize() 
    {
        SourceIpAddresses = new ArrayList<>();
    }

    /**
     * Get the top 10 data
     * @param Flows The flow data to analyze
     * @return The top 10 source IP addresses in the data
     */
    @Override
    public Map<String, Integer> GetData(ArrayList<Flow> Flows) 
    {
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourceHost() != null) 
           {
               SourceIpAddresses.add(Flows.get(i).GetSourceHost().getHostAddress());
           }
        }     
        return Utility.GetTopElements(SourceIpAddresses, 9); 
    }
}
