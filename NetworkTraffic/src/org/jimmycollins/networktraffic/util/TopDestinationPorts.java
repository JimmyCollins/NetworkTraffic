package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Map;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.TopData;

/**
 * Part of Template Pattern Implementation
 */
public class TopDestinationPorts extends TopData
{
    private ArrayList DestinationPorts;

    /**
     * Initialize the data
     */
    @Override
    public void Initialize()
    {
        DestinationPorts = new ArrayList<>();
    }

    /**
     * Get the top 10 data
     * @param Flows The flow data to analyze
     * @return The top 10 destination ports in the data
     */
    @Override
    public Map<String,Integer> GetData(ArrayList<Flow> Flows)
    {
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetDestinationPort()!= -1) 
           {
               int port = Flows.get(i).GetDestinationPort();
               DestinationPorts.add(Integer.toString(port));
           }
        }    
        
        return Utility.GetTopElements(DestinationPorts, 9); 
    }
}
