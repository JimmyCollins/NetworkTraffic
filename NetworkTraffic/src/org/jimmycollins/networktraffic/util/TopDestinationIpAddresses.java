package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Map;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.TopData;

/**
 * Part of Template Pattern Implementation
 */
public class TopDestinationIpAddresses extends TopData
{
    private ArrayList DestinationIpAddresses;

    /**
     * Initialize the data
     */
    @Override
    public void Initialize() 
    {
        DestinationIpAddresses = new ArrayList<>();
    }

    /**
     * Get the top 10 data
     * @param Flows The flow data to analyze
     * @return The top 10 destination IP addresses in the data
     */
    @Override
    public Map<String, Integer> GetData(ArrayList<Flow> Flows) 
    {
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetDestinationHost()!= null) 
           {
               DestinationIpAddresses.add(Flows.get(i).GetDestinationHost().getHostAddress());
           }
        }     
        return Utility.GetTopElements(DestinationIpAddresses, 9); 
    }
}