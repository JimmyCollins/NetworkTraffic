package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Map;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.TopData;

public class TopDestinationIpAddresses extends TopData
{
    private ArrayList DestinationIpAddresses;

    @Override
    public void Initialize() 
    {
        DestinationIpAddresses = new ArrayList<>();
    }

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
