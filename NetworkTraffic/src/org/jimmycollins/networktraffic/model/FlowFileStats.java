
package org.jimmycollins.networktraffic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jimmycollins.networktraffic.util.Utility;


public class FlowFileStats 
{      
    private final List<Observer> Observers = new ArrayList<>();
    
    public static int ParsedFlows;
    
    private static int UnparsableFlows;
    
    private ArrayList<Flow> Flows;
    
    public FlowFileStats()
    {
        ParsedFlows = 0;
        UnparsableFlows = 0;
        Flows = new ArrayList();
        
    }
    
    public void IncrementFlowCounter()
    {
        ParsedFlows++;
        notifyAllObservers();
    }
    
    public void IncrementUnparsableFlowCounter()
    {
        UnparsableFlows++;
        notifyAllObservers();
    }
    
    public int GetParsedFlows()
    {
        return ParsedFlows;
    }
    
    public int GetUnparsableFlows()
    {
        return UnparsableFlows;
    }
    
    
    public void AddFlow(Flow flow)
    {
        this.Flows.add(flow);
    }
    
    
    public ArrayList<Flow> GetFlows()
    {
        return Flows;
    }
    
    
    // Observer Pattern stuff
    public void attach(Observer observer)
    {
      Observers.add(observer);		
    }

    public void notifyAllObservers()
    {
        Observers.stream().forEach((observer) -> {
            observer.update();
        });
   }	
    
    
    // TODO: Comment these
    
    public Map<String,Integer> GetTop5SourcePorts()
    {
        ArrayList<String> sourcePorts = new ArrayList<>();  
        
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               int port = Flows.get(i).GetSourcePort();
               sourcePorts.add(Integer.toString(port));
           }
        }
        
        return Utility.GetTopElements(sourcePorts, 4);    
    }
    
    public Map<String,Integer> GetTop5DestinationPorts()
    {
        ArrayList<String> destinationPorts = new ArrayList<>();  
        
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               int port = Flows.get(i).GetDestinationPort();
               destinationPorts.add(Integer.toString(port));
           }
        }
        
        return Utility.GetTopElements(destinationPorts, 4);    
    }
     
    public Map<String,Integer> GetTop5SourceIPAddresses()
    {
        ArrayList<String> sourceIPs = new ArrayList<>();   
             
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               sourceIPs.add(Flows.get(i).GetSourceHost().getHostAddress());
           }
        }
        
        return Utility.GetTopElements(sourceIPs, 4);    
    }
    
    public Map<String,Integer> GetTop5DestinationIPAddresses()
    {
        ArrayList<String> destinationIPs = new ArrayList<>();     
        
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               destinationIPs.add(Flows.get(i).GetDestinationHost().getHostAddress());
           }
        }
        
        return Utility.GetTopElements(destinationIPs, 4);    
    }
    
    public Map<String,Integer> GetTop5Protocols()
    {
        ArrayList<String> protocols = new ArrayList<>();  
        
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               protocols.add(Flows.get(i).GetFlowType());
           }
        }
        
        return Utility.GetTopElements(protocols, 4);    
    } 
    
}
