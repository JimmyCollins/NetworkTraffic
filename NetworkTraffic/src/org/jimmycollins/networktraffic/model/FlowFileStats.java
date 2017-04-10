
package org.jimmycollins.networktraffic.model;

import java.util.ArrayList;
import java.util.List;

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
}
