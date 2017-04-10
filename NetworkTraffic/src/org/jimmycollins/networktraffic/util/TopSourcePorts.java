package org.jimmycollins.networktraffic.util;

import java.util.ArrayList;
import java.util.Map;
import org.jimmycollins.networktraffic.model.Flow;
import org.jimmycollins.networktraffic.model.TopData;

public class TopSourcePorts extends TopData
{
    private ArrayList SourcePorts;

    @Override
    public void Initialize()
    {
        SourcePorts = new ArrayList<>();
    }

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