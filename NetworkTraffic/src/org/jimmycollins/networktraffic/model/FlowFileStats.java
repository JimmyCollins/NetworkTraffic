
package org.jimmycollins.networktraffic.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jimmycollins.networktraffic.util.Utility;

public class FlowFileStats 
{    
    
    private List<Observer> observers = new ArrayList<Observer>();
    
    public static int ParsedFlows = 0;
    
    private static int UnparsableFlows;
    
    private static ArrayList<InetAddress> SourceHosts;
    
    private static ArrayList<InetAddress> DestinationHosts;
    
    private static ArrayList<Integer> SourcePorts;
    
    private static ArrayList<Integer> DestinationPorts;
    
    private static ArrayList<String> Protocols;
    
    public FlowFileStats()
    {
        ParsedFlows = 0;
        UnparsableFlows = 0;
        SourceHosts = new ArrayList<>();
        DestinationHosts = new ArrayList<>();
        SourcePorts = new ArrayList<>();
        DestinationPorts = new ArrayList<>();
        Protocols = new ArrayList();
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
    
    public void AddSourceHost(InetAddress host)
    {
        SourceHosts.add(host);
    }
    
    public void AddDestinationHost(InetAddress host)
    {
        DestinationHosts.add(host);
    }
    
    public void AddSourcePort(Integer port)
    {
        SourcePorts.add(port);
    }
    
    public void AddDestinationPort(Integer port)
    {
        DestinationPorts.add(port);
    }
    
    public void AddProtocol(String protocol)
    {
        Protocols.add(protocol);
    }
    
    public ArrayList<InetAddress> GetSourceHosts()
    {
        return SourceHosts;
    }
    
    public ArrayList<InetAddress> GetDestinationHosts()
    {
        return DestinationHosts;
    }
    
    public ArrayList<Integer> GetSourcePorts()
    {
        return SourcePorts;
    }
    
    public ArrayList<Integer> GetDestinationPorts()
    {
        return DestinationPorts;
    }
    
    public int GetParsedFlows()
    {
        return ParsedFlows;
    }
    
    public int GetUnparsableFlows()
    {
        return UnparsableFlows;
    }
    
    public ArrayList<String> GetProtocols()
    {
        return Protocols;
    }
    
    
    // Observer Patter stuff
    public void attach(Observer observer){
      observers.add(observer);		
   }

    public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   }	
    
    // TODO: New class? Consolidate/parameterize these?
    
    public Map<String,Integer> GetTop5SourcePorts()
    {
        ArrayList<String> sourcePorts = new ArrayList<>();     
        for(int i=0; i<SourcePorts.size();i++)
        {
            if(SourcePorts.get(i) != -1)
            {
                sourcePorts.add(SourcePorts.get(i).toString());
            }
        }
        
        return Utility.GetTopElements(sourcePorts, 4);    
    }
     
    public Map<String,Integer> GetTop5DestinationPorts()
    {
        ArrayList<String> destinationPorts = new ArrayList<>();      
        for(int i=0; i<DestinationPorts.size();i++)
        {
            if(DestinationPorts.get(i) != -1)
            {
                destinationPorts.add(DestinationPorts.get(i).toString());
            }
        }
        
        return Utility.GetTopElements(destinationPorts, 4);    
    }
      
    public Map<String,Integer> GetTop5SourceIPAddresses()
    {
        ArrayList<String> sourceIPs = new ArrayList<>();     
        for(int i=0; i<SourceHosts.size();i++)
        {
            if(SourceHosts.get(i) != null)
            {
                sourceIPs.add(SourceHosts.get(i).getHostAddress());
            }
        }
        
        return Utility.GetTopElements(sourceIPs, 4);    
    }
    
    public Map<String,Integer> GetTop5DestinationIPAddresses()
    {
        ArrayList<String> destinationIPs = new ArrayList<>();     
        for(int i=0; i<DestinationHosts.size();i++)
        {
            if(DestinationHosts.get(i) != null)
            {
                destinationIPs.add(DestinationHosts.get(i).getHostAddress());
            }
        }
        
        return Utility.GetTopElements(destinationIPs, 4);    
    }
    
    public Map<String,Integer> GetTop5Protocols()
    {
        ArrayList<String> protocols = new ArrayList<>();     
        for(int i=0; i<Protocols.size();i++)
        {
            if(Protocols.get(i) != null || !Protocols.get(i).isEmpty())
            {
                protocols.add(Protocols.get(i).toUpperCase());
            }
        }
        
        return Utility.GetTopElements(protocols, 4);    
    } 
    
}
