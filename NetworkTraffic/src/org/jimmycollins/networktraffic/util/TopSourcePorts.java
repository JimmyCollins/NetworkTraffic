package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Map;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.TopData;

/**
 * Part of Template Pattern Implementation
 */
public class TopSourcePorts extends TopData
{
    private ArrayList SourcePorts;

    /**
     * Initialize the data
     */
    @Override
    public void Initialize()
    {
        SourcePorts = new ArrayList<>();
    }

    /**
     * Get the top 10 data
     * @param Flows The flow data to analyze
     * @return The top 10 source ports in the data
     */
    @Override
    public Map<String,Integer> GetData(ArrayList<Flow> Flows)
    {
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               int port = Flows.get(i).GetSourcePort();
               SourcePorts.add(Integer.toString(port));
           }
        }    
        
        return Utility.GetTopElements(SourcePorts, 9); 
    }
}