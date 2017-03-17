
package org.jimmycollins.networktraffic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jimmycollins.networktraffic.util.Utility;

/**
 * High level Object for storing statistics from the traffic file
 */
public class FlowFileStats 
{      
    private final List<Observer> Observers = new ArrayList<>();
    
    private final ArrayList<Flow> Flows;
    
    private static int ParsedFlows;
    
    private static int UnparsableFlows;
    
    private static int TotalPackets;
    
    /**
     * Constructor
     */
    public FlowFileStats()
    {
        ParsedFlows = 0;
        UnparsableFlows = 0;
        TotalPackets = 0;
        Flows = new ArrayList();      
    }
    
    /**
     * Account for a new parsing error
     */
    public void IncrementUnparsableFlowCounter()
    {
        UnparsableFlows++;
        notifyAllObservers();
    }
    
    /**
     * Add a new parsed flow
     * @param flow The flow to add
     */
    public void AddFlow(Flow flow)
    {
        this.Flows.add(flow);
        ParsedFlows++;
        notifyAllObservers();
    }
    
    /**
     * Add to the parsed packet count
     * @param packetCount The count to add
     */
    public void AddPackets(int packetCount)
    {
        TotalPackets += packetCount;
    }
    
    /**
     * Get the parsed flows
     * @return The current count of parsed flows
     */
    public int GetParsedFlows()
    {
        return ParsedFlows;
    }
    
    /**
     * Get the unparsable flows
     * @return The current count of parsing errors
     */
    public int GetUnparsableFlows()
    {
        return UnparsableFlows;
    }
    
    /**
     * Get a list of Flow objects currently parsed
     * @return The list of Flow objects currently parsed
     */
    public ArrayList<Flow> GetFlows()
    {
        return Flows;
    }
    
    /**
     * Get the total packet count
     * @return The current total packet count
     */
    public int GetTotalPacketCount()
    {
        return TotalPackets;
    }
       
    /**
     * Add an observer to this object
     * @param observer The observer to add
     */
    public void attach(Observer observer)
    {
      Observers.add(observer);		
    }

    /**
     * Notify all observers of changes
     */
    public void notifyAllObservers()
    {
        Observers.stream().forEach((observer) -> {
            observer.update();
        });
    }	
    
    /**
     * Get the top 10 source ports from the currently parsed data
     * @return The top 10 source ports
     */
    public Map<String,Integer> GetTopSourcePorts()
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
        return Utility.GetTopElements(sourcePorts, 9);    
    }
    
    /**
     * Get the top 10 destination ports from the currently parsed data
     * @return The top 10 destination ports
     */
    public Map<String,Integer> GetTopDestinationPorts()
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
        return Utility.GetTopElements(destinationPorts, 9);    
    }
     
    /**
     * Get the top 10 source IP addresses from the currently parsed data
     * @return The top 10 source IP addresses
     */
    public Map<String,Integer> GetTopSourceIPAddresses()
    {
        ArrayList<String> sourceIPs = new ArrayList<>();   
             
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               sourceIPs.add(Flows.get(i).GetSourceHost().getHostAddress());
           }
        }     
        return Utility.GetTopElements(sourceIPs, 9);    
    }
    
    /**
     * Get the top 10 destination IP addresses from the currently parsed data
     * @return The top 10 destination IP addresses
     */
    public Map<String,Integer> GetTopDestinationIPAddresses()
    {
        ArrayList<String> destinationIPs = new ArrayList<>();     
        
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               destinationIPs.add(Flows.get(i).GetDestinationHost().getHostAddress());
           }
        }  
        return Utility.GetTopElements(destinationIPs, 9);    
    }
    
    /**
     * Get the top 10 protocols from the currently parsed data
     * @return The top 10 protocols
     */
    public Map<String,Integer> GetTopProtocols()
    {
        ArrayList<String> protocols = new ArrayList<>();  
        
        for(int i=0; i<Flows.size();i++)
        {
           if(Flows.get(i).GetSourcePort() != -1) 
           {
               protocols.add(Flows.get(i).GetFlowType());
           }
        }      
        return Utility.GetTopElements(protocols, 9);    
    } 
}
