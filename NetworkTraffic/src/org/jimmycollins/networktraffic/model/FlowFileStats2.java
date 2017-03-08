

package org.jimmycollins.networktraffic.model;

import java.util.ArrayList;
import java.util.List;


public class FlowFileStats2 
{   
    // We'll want to retain the high level stuff so that it 
    // can still be observed by MainController
    
    // For the rest, try group into different packet types (based on port number??)
    
    // A factory can be used to create the differnet packet types during pasing
    
    // This will probably require some rework of the strategy pattern implementation
    
    private final List<Observer> observers = new ArrayList<>();
    
    public static int ParsedFlows;
    
    private static int UnparsableFlows;
    
    public FlowFileStats2()
    {
        ParsedFlows = 0;
        UnparsableFlows = 0;
    }
    
    public void AddFlow()
    {
        ParsedFlows++;
        notifyAllObservers();
    }
    
    public void AddUnparsableFlow()
    {
        UnparsableFlows++;
    }
    
    public int GetParsedFlows()
    {
        return ParsedFlows;
    }
    
    public int GetUnparsableFlows()
    {
        return UnparsableFlows;
    }
    
    // Observer Pattern stuff
    public void attach(Observer observer)
    {
      observers.add(observer);		
    }

    public void notifyAllObservers()
    {
        observers.stream().forEach((observer) -> {
            observer.update();
        });
   }	
    
}
